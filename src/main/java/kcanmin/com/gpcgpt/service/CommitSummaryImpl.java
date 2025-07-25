package kcanmin.com.gpcgpt.service;

import kcanmin.com.gpcgpt.domain.dto.CommitDto;
import kcanmin.com.gpcgpt.domain.dto.CommitSummaryRequest;
import kcanmin.com.gpcgpt.domain.dto.CommitSummaryResponse;
import kcanmin.com.gpcgpt.domain.dto.RepositoryDto;
import kcanmin.com.gpcgpt.domain.entity.Commit;
import kcanmin.com.gpcgpt.domain.entity.CommitSummary;
import kcanmin.com.gpcgpt.domain.entity.Repository;
import kcanmin.com.gpcgpt.repository.CommitRepository;
import kcanmin.com.gpcgpt.repository.CommitSummaryRepository;
import kcanmin.com.gpcgpt.repository.RepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommitSummaryServiceImpl implements CommitSummaryService {

  private final GitHubClient gitHubClient;
  private final GptService gptClient;
  private final RepositoryRepository repositoryRepository;
  private final CommitRepository commitRepository;
  private final CommitSummaryRepository commitSummaryRepository;

  @Override
  public RepositoryDto summarizeRecent(CommitSummaryRequest request) {

    // 저장소 정보 등록 또는 조회
    Repository repo = repositoryRepository.findByUrl(request.getUrl())
      .orElseGet(() -> repositoryRepository.save(
        Repository.builder()
          .rName(request.getRName())
          .url(request.getUrl())
          .build()
      ));

    // 최근 커밋 가져오기
    List<CommitDto> commitDtos = gitHubClient.getRecentCommits(request.getUrl());

    List<CommitSummaryResponse> responses = new ArrayList<>();

    for (CommitDto dto : commitDtos) {
      if (commitRepository.existsByHash(dto.getHash())) continue;

      // diff 추출 후 GPT 요약
      String diff = gitHubClient.getCommitDiff(request.getUrl(), dto.getHash());
      String summaryText = gptClient.summarize(dto.getMsg(), diff);

      Commit commit = commitRepository.save(Commit.builder()
              .hash(dto.getHash())
              .msg(dto.getMsg())
              .author(dto.getAuthor())
              .rno(repo)
              .build());

      commitSummaryRepository.save(CommitSummary.builder()
              .cno(commit)
              .summary(summaryText)
              .build());

      responses.add(CommitSummaryResponse.builder()
              .hash(dto.getHash())
              .msg(dto.getMsg())
              .cName(dto.getCName())
              .summary(summaryText)
              .regDate(commit.getRegDate())
              .build());
    }

    return RepositoryDto.builder()
            .rno(repo.getRno())
            .rName(repo.getRName())
            .url(repo.getUrl())
            .build();
  }
}


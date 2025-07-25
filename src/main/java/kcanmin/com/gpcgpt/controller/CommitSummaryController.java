package kcanmin.com.gpcgpt.controller;

import kcanmin.com.gpcgpt.domain.dto.CommitSummaryRequest;
import kcanmin.com.gpcgpt.domain.dto.CommitSummaryResponse;
import kcanmin.com.gpcgpt.domain.dto.RepositoryDto;
import kcanmin.com.gpcgpt.service.CommitSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommitSummaryController {
  private final CommitSummaryService commitSummaryService;

  @PostMapping
  public ResponseEntity<RepositoryDto> commitSummary(@RequestBody CommitSummaryRequest commitSummaryRequest) {
    return null;
  }
}

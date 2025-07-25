package kcanmin.com.gpcgpt.service;

import kcanmin.com.gpcgpt.domain.dto.CommitDto;

import java.util.List;

public interface GitHubService {
  List<CommitDto> getRecentCommits(String repoUrl);
  String getCommitDiff(String repoUrl, String sha);
}

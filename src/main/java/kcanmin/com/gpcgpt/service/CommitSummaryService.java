package kcanmin.com.gpcgpt.service;


import kcanmin.com.gpcgpt.domain.dto.CommitSummaryRequest;
import kcanmin.com.gpcgpt.domain.dto.RepositoryDto;
import org.springframework.stereotype.Service;

public interface CommitSummaryService {
  RepositoryDto summarizeRecent(CommitSummaryRequest commitSummaryRequest);
}

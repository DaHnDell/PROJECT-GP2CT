package kcanmin.com.gpcgpt.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommitSummaryRequest {

  private String rName; // repositoryName
  private String url; // repositoryURL

}

package kcanmin.com.gpcgpt.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommitSummaryResponse {
  private String hash; // commitHash
  private String msg; // commitMessage
  private String cName; // commitName
  private String summary; // commitSummary
  private LocalDateTime regDate; //
}

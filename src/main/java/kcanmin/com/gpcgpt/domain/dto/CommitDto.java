package kcanmin.com.gpcgpt.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommitDto {
  private Long cno; // commitNO
  private String hash; // commitHash
  private String msg; // commitMessage
  private String cName; // commitName
  private String author; // commitAuthors
}

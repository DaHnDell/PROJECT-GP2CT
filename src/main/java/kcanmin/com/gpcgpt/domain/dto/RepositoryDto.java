package kcanmin.com.gpcgpt.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RepositoryDto {
  private Long rno; // repositoryNo
  private String rName; // repositoryName
  private String url; // repositoryURL
}

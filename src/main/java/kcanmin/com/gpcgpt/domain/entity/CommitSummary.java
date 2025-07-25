package kcanmin.com.gpcgpt.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CommitSummary extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "csno")
  private Long csno;

  @OneToOne
  @JoinColumn(name = "cno")
  private Commit cno;

  private String summaryText;

}

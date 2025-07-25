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
  private Long csno; // commitSummaryNo

  @OneToOne
  @JoinColumn(name = "cno") // commitNo
  private Commit cno;

  private String summary; // summary

}

package kcanmin.com.gpcgpt.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Commit extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cno;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rno")
  private Repository rno; // repositoryNo

  private String hash; // commitHash

  private String msg; // commitMessage

  private String author; // commitAuthor

}

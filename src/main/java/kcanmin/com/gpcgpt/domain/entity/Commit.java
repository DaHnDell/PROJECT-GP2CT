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
  private Repository rno;

  private String commitHash;

  private String commitMessage;

  private String authorName;

}

package kcanmin.com.gpcgpt.repository;

import kcanmin.com.gpcgpt.domain.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Long> {
  boolean existsByHash(String hash);
}

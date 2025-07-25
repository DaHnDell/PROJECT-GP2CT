package kcanmin.com.gpcgpt.repository;

import kcanmin.com.gpcgpt.domain.entity.CommitSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitSummaryRepository extends JpaRepository<CommitSummary, Long> {
}

package kcanmin.com.gpcgpt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@ToString
public class BaseEntity {

  @CreatedDate
  @Column(name = "regDate", updatable = false)
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name = "modDate")
  private LocalDateTime modDate;

}
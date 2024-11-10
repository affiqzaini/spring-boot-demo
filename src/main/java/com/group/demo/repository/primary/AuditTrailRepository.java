package com.group.demo.repository.primary;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.group.demo.entity.primary.AuditTrail;

public interface AuditTrailRepository extends PagingAndSortingRepository<AuditTrail, Long> {

    AuditTrail save(AuditTrail auditTrail);

}

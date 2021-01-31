package com.atlaspharmacy.atlaspharmacy.reports.repository;

import com.atlaspharmacy.atlaspharmacy.reports.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}

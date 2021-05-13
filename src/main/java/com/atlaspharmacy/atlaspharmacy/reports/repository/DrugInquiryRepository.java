package com.atlaspharmacy.atlaspharmacy.reports.repository;

import com.atlaspharmacy.atlaspharmacy.reports.domain.DrugInquiryReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugInquiryRepository extends JpaRepository<DrugInquiryReport, Long> {
}

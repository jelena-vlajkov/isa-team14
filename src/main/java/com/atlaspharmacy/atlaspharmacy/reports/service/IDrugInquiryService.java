package com.atlaspharmacy.atlaspharmacy.reports.service;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugInquiryReportDTO;
import org.springframework.stereotype.Service;

@Service
public interface IDrugInquiryService {
    void addDrugInquiry(DrugInquiryReportDTO drugInquiryReportDTO);
}

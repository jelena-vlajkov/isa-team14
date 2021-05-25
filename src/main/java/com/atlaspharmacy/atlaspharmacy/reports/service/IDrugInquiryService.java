package com.atlaspharmacy.atlaspharmacy.reports.service;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugInquiryReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDrugInquiryService {
    void addDrugInquiry(DrugInquiryReportDTO drugInquiryReportDTO);
    List<DrugInquiryReportDTO> getAll();
}

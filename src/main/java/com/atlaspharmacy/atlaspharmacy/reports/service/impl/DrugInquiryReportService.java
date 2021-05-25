package com.atlaspharmacy.atlaspharmacy.reports.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugInquiryReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.domain.DrugInquiryReport;
import com.atlaspharmacy.atlaspharmacy.reports.mapper.DrugInquiryMapper;
import com.atlaspharmacy.atlaspharmacy.reports.repository.DrugInquiryRepository;
import com.atlaspharmacy.atlaspharmacy.reports.service.IDrugInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugInquiryReportService implements IDrugInquiryService {
    private final DrugInquiryRepository drugInquiryRepository;
    private final MedicationServiceImpl medicationService;

    @Autowired
    public DrugInquiryReportService(DrugInquiryRepository drugInquiryRepository, MedicationServiceImpl medicationService) {
        this.drugInquiryRepository = drugInquiryRepository;
        this.medicationService = medicationService;
    }

    @Override
    public void addDrugInquiry(DrugInquiryReportDTO drugInquiryReportDTO) {
        DrugInquiryReport drugInquiryReport=new DrugInquiryReport();
        drugInquiryReport.setDate(drugInquiryReportDTO.getDate());
        drugInquiryReport.setMedication(MedicationMapper.convertToMedication(medicationService.findById(drugInquiryReportDTO.getMedication().getId())));
        drugInquiryRepository.save(drugInquiryReport);
    }

    @Override
    public List<DrugInquiryReportDTO> getAll() {
        return DrugInquiryMapper.mapToListDTOS(drugInquiryRepository.findAll());
    }
}

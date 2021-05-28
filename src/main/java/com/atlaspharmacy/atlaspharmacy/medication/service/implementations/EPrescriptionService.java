package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.PrescribedDrugDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.EPrescriptionMapper;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.PrescribedDrugMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.EPrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.repository.PrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EPrescriptionService implements IEPrescriptionService {
    private final EPrescriptionRepository ePrescriptionRepository;
    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicationRepository medicationRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public EPrescriptionService(EPrescriptionRepository ePrescriptionRepository, UserRepository userRepository, PharmacyRepository pharmacyRepository, MedicationRepository medicationRepository, PrescriptionRepository prescriptionRepository) {
        this.ePrescriptionRepository = ePrescriptionRepository;
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.medicationRepository = medicationRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public List<EPrescription> getPatientsEPrescription(Long id) {
        return ePrescriptionRepository.findByPatientId(id);
    }

    @Override
    public void saveNewPrescription(CreateDrugReservationDTO dto) throws Exception {
        EPrescription ePrescription = new EPrescription();
        if (!userRepository.findById(dto.getPatientId()).isPresent()) {
            throw new Exception("Invalid request");
        }

        if (!pharmacyRepository.findById(dto.getPharmacyId()).isPresent()) {
            throw new Exception("Invalid request");
        }

        if (!medicationRepository.findById(dto.getMedicationId()).isPresent()) {
            throw new Exception("Invalid request");
        }
        Pharmacy pharmacy = pharmacyRepository.findById(dto.getPharmacyId()).get();
        Patient patient = (Patient) userRepository.findById(dto.getPatientId()).get();
        ePrescription.setName(patient.getName());
        ePrescription.setSurname(patient.getSurname());
        ePrescription.setDate(new Date());
        ePrescription.setPharmacy(pharmacy);
        ePrescription.setPatient(patient);

        ePrescriptionRepository.save(ePrescription);

        Medication m = medicationRepository.findById(dto.getMedicationId()).get();

        PrescribedDrug prescribedDrug = new PrescribedDrug();
        prescribedDrug.setEprescription(ePrescription);
        prescribedDrug.setTherapyDays(dto.getTherapyDays());
        prescribedDrug.setMedication(m);
        prescribedDrug.setQuantity(new Long(dto.getTherapyDays()));

        prescriptionRepository.save(prescribedDrug);
    }

    public List<EPrescriptionDTO> getAllEPrescritpions(Long patientId) {
        List<EPrescriptionDTO> ePrescriptionDTOS = new ArrayList<>();
        List<EPrescription> ePrescriptions = getPatientsEPrescription(patientId);
        for (EPrescription e : ePrescriptions) {
            ePrescriptionDTOS.add(EPrescriptionMapper.mapEPrescriptionToDTO(e));
        }

        return ePrescriptionDTOS;

    }

    @Override
    public List<PrescribedDrugDTO> getAllPrescribedDrugForPatient(Long patientId) {
       List<PrescribedDrug> prescribedDrugs = prescriptionRepository.getPrescribedDrugBy(patientId);
       List<PrescribedDrugDTO> prescribedDrugDTOS = new ArrayList<>();

       for (PrescribedDrug p : prescribedDrugs) {
           prescribedDrugDTOS.add(PrescribedDrugMapper.drugToDto(p));
       }

       return prescribedDrugDTOS;

    }


}

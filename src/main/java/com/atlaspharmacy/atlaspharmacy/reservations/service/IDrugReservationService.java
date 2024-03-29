package com.atlaspharmacy.atlaspharmacy.reservations.service;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.PatientDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public interface IDrugReservationService {
    void reserveDrug(CreateDrugReservationDTO drugReservationDTO) throws Exception;
    boolean cancelDrugReservation(Long reservationId);
    boolean issueDrugReservation(int uniqueIdentifier, Long medicalStaffId) throws Exception;
    DrugReservation findDrugReservation(int uniqueIdentifier, Long medicalStaffId) throws Exception;
    List<DrugReservationDTO> findAllReservation(Long pharmacyId);
    List<DrugReservation> getPatientsIssuedDrugReservations(Long id);
    List<DrugReservation> findAllIssuedReservationsForPharmacyAndPeriod(Long pharmacyId, PeriodDTO periodDTO);
    void patientDrugReservation(CreateDrugReservationDTO drugReservationDTO) throws Exception;
    List<PatientDrugReservationDTO> getDrugReservationForPatient(Long patientId);
    boolean isDrugReserved(Long medicationId,Long pharmacyId);
}

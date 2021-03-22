package com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.SubscriptionDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionMapper {
    private SubscriptionMapper(){}
    public static SubscriptionDTO mapSubscriptionToDTO(Subscription subscription){
        return new SubscriptionDTO(subscription.getId(), PharmacyMapper.mapPharmacyToDTO(subscription.getPharmacy()), PatientMapper.mapPatientToDTO(subscription.getPatient()));
    }
    public static Subscription mapDTOToSubscription(SubscriptionDTO dto){
        Subscription s = new Subscription();
        s.setId(dto.getId());
        s.setPatient(PatientMapper.mapDTOToPatient(dto.getPatient()));
        s.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
        return s;
    }
    public static List<SubscriptionDTO> mapToDTOS(List<Subscription> subscriptions){
        List<SubscriptionDTO> dtos = new ArrayList<>();
        for(Subscription s : subscriptions){
            dtos.add(mapSubscriptionToDTO(s));
        }

        return dtos;
    }
}

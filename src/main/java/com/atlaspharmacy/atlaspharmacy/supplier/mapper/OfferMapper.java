package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;

import java.util.ArrayList;
import java.util.List;

public class OfferMapper {
    private OfferMapper(){}

    public static OfferDTO mapOfferToDTO(Offer offer){
        OfferDTO dto = new OfferDTO();
        dto.setId(offer.getId());
        dto.setOfferStatus(offer.getOfferStatus());
        dto.setDueDelivery(offer.getDueDelivery());
        dto.setOrder(OrderMapper.mapOrderToDTO(offer.getOrder()));
        dto.setSupplier(SupplierMapper.mapSupplierToDTO(offer.getSupplier()));
        dto.setPrice(offer.getPrice());
        dto.setUniqueidentifier(offer.getUniqueidentifier());
        return dto;
    }
    public static List<OfferDTO> mapToListDTOS(List<Offer> offers){
        List<OfferDTO> dtos = new ArrayList<>();
        for(Offer o : offers){
            dtos.add(mapOfferToDTO(o));
        }
        return dtos;
    }

}

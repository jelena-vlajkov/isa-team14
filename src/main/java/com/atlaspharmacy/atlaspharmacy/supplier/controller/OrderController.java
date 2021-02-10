package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SupplierAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.mapper.AppointmentMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import com.atlaspharmacy.atlaspharmacy.supplier.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final IOrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public @ResponseBody
    List<OrderDTO> getUnfinishedOrders(){
        return orderService.getAllunfinishedOrders();
    }

    @GetMapping(value = "/getByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public @ResponseBody
    OrderDTO getByIdentifier(@RequestParam("id") int id){
        return orderService.getByIdentifier(id);
    }

    @GetMapping(value = "/getOrderedMedicationByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public @ResponseBody
    List<OrderedMedicationDTO> getOrderedMedicationByIdentifier(@RequestParam("id") int id){
        return orderService.getOrderedMedicationByIdentifier(id);
    }

    @GetMapping(value = "/getAllOrdersWehereOfferIsNotGivenBySupplier", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public @ResponseBody
    List<OrderDTO> getAllOrdersWehereOfferIsNotGivenBySupplier(@RequestParam("id") Long id){
        return orderService.getAllOrdersWehereOfferIsNotGivenBySupplier(id);
    }

}

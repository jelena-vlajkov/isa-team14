package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.*;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import com.atlaspharmacy.atlaspharmacy.supplier.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @OrderAuthorization
    public @ResponseBody
    List<OrderDTO> getUnfinishedOrders(){
        return orderService.getAllunfinishedOrders();
    }

    @GetMapping(value = "/getByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @OrderAuthorization
    public @ResponseBody
    OrderDTO getByIdentifier(@RequestParam("id") int id){
        return orderService.getByIdentifier(id);
    }

    @GetMapping(value = "/getOrderedMedicationByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @OrderAuthorization
    public @ResponseBody
    List<OrderedMedicationDTO> getOrderedMedicationByIdentifier(@RequestParam("id") int id){
        return orderService.getOrderedMedicationByIdentifier(id);
    }

    @PostMapping(value = "/addOrder", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.addOrder(orderDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrdersWehereOfferIsNotGivenBySupplier", produces = MediaType.APPLICATION_JSON_VALUE)
    @OrderAuthorization
    public @ResponseBody
    List<OrderDTO> getAllOrdersWhereOfferIsNotGivenBySupplier(@RequestParam("id") Long id){
        return orderService.getAllOrdersWhereOfferIsNotGivenBySupplier(id);
    }

    @PostMapping (value = "/filterOrdersByStatus")
    public ResponseEntity<?> filterOrdersByStatus(@RequestBody String status) {
        try {
            List<OrderDTO> order = orderService.filterOrdersByState(status);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

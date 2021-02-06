package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {
    private final IOrderService _orderService;

    @Autowired
    public OrderController(IOrderService _orderService) {
        this._orderService = _orderService;
    }

    @PostMapping(value = "/addOrder", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = _orderService.addOrder(orderDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

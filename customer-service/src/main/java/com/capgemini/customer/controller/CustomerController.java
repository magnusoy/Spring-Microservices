package com.capgemini.customer.controller;

import com.capgemini.customer.VO.ResponseTemplateVO;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseTemplateVO>> findAllCustomers() {
        log.info("Inside findCustomerById method of CustomerController");

        List<ResponseTemplateVO> responseTemplateVOS = customerService.getCustomersWithTeamAndEngagements();
        if (responseTemplateVOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseTemplateVOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> findCustomerById(@PathVariable("id") Long customerId) {
        log.info("Inside findCustomerById method of CustomerController");
        ResponseTemplateVO responseTemplateVO = customerService.getCustomerWithTeamAndEngagements(customerId);
        if (responseTemplateVO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseTemplateVO);
    }

    @PostMapping("/")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        log.info("Inside saveCustomer method of CustomerController");
        customerService.saveCustomer(customer);
        URI uri = URI.create("/customers/" + customer.getCustomerId());
        return ResponseEntity.created(uri).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        if (customerService.findCustomerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Customer updatedCustomer = customerService.updateCustomer(customer);
        URI uri = URI.create("/customers/" + updatedCustomer.getCustomerId());
        return ResponseEntity.created(uri).body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable("id") Long customerId) {
        if (customerService.findCustomerById(customerId) == null) {
            return ResponseEntity.notFound().build();
        }
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

package com.capgemini.customer.service;


import com.capgemini.customer.VO.Engagement;
import com.capgemini.customer.VO.ResponseTemplateVO;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.repository.CustomerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CustomerService implements  CustomerServiceInterface{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Inside saveCustomer method of CustomerService");
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        log.info("Inside findCustomerById method of CustomerService");
        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        log.info("Inside deleteCustomerById method of CustomerService");
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        log.info("Inside updateCustomer method of CustomerService");
        return customerRepository.save(customer);
    }

    @Override
    public ResponseTemplateVO getCustomerWithTeamAndEngagements(Long customerId) {
        log.info("Inside getCustomerWithTeamAndEngagements method of CustomerService");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        ResponseEntity<Engagement[]> engagements;
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            return null;
        }
        if (!customer.getEngagements().isEmpty()) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://ENGAGEMENT-SERVICE/engagements/");
            builder.queryParam("ids", String.join(",", (CharSequence) customer.getEngagements()));
            URI uri = builder.build().encode().toUri();
            engagements = restTemplate.getForEntity(uri,
                    Engagement[].class);
            responseTemplateVO.setEngagements(Arrays.asList(Objects.requireNonNull(engagements.getBody())));
        } else {
            responseTemplateVO.setEngagements(new ArrayList());
        }
        responseTemplateVO.setCustomer(customer);
        return responseTemplateVO;
    }

    @Override
    public List<ResponseTemplateVO> getCustomersWithTeamAndEngagements() {
        log.info("Inside getCustomersWithTeamAndEngagements method of CustomerService");
        List<ResponseTemplateVO> responseTemplateVOS;
        responseTemplateVOS = customerRepository.findAll()
                .stream()
                .map(customer -> getCustomerWithTeamAndEngagements(customer.getCustomerId()))
                .collect(Collectors.toList());
        return responseTemplateVOS;
    }

    private boolean isObjectPresent(Long id) {
        return id != null;
    }
}

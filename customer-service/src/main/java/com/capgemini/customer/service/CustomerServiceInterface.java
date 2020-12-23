package com.capgemini.customer.service;

import com.capgemini.customer.VO.ResponseTemplateVO;
import com.capgemini.customer.entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Customer customer);

    ResponseTemplateVO getCustomerWithTeamAndEngagements(Long customerId);

    List<ResponseTemplateVO> getCustomersWithTeamAndEngagements();
}

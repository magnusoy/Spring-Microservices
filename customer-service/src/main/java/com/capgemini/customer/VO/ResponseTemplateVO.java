package com.capgemini.customer.VO;

import com.capgemini.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Customer customer;
    private List<Engagement> engagements;
}

package com.capgemini.customer.entity;


import com.capgemini.customer.validators.CustomerNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String customerAvatar;
    @CustomerNameConstraint
    @NotBlank(message = "Customer name is mandatory")
    private String customerName;
    private String contactName;
    private String email;
    private String phone;
    private String ourContact;
    private String note;
    private String industry;
    @ElementCollection
    private List<Long> engagements;
}

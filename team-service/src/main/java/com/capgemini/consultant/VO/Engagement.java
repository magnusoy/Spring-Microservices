package com.capgemini.consultant.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engagement {
    private Long engagementId;
    private String title;
    private Date startDate;
    private Date endDate;
    private Date expireDate;
    private Integer percentage;
    private Status Status;
    private String owner;
    private String description;
    private Long customerId;
    private Customer customer;
}

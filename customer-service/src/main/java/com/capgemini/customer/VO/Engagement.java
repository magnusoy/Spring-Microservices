package com.capgemini.customer.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<Consultant> consultants;
}

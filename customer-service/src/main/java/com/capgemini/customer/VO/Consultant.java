package com.capgemini.customer.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultant {
    private Long consultantId;
    private String profileImage;
    private String lastName;
    private String firstName;
}

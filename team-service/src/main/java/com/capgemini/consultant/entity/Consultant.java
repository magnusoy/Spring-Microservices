package com.capgemini.consultant.entity;

import com.capgemini.consultant.validators.FirstNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consultantId;
    private String profileImage;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @FirstNameConstraint
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    private String email;
    private Date startDate;
    private Long teamId;
    @ElementCollection
    private List<Long> engagements;
}

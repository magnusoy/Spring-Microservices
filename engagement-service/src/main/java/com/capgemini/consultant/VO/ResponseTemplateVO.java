package com.capgemini.consultant.VO;

import com.capgemini.consultant.entity.Consultant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Consultant consultant;
    private List<Engagement> engagements;
    private Team team;
}

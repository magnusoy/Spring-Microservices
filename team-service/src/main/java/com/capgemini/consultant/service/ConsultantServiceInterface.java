package com.capgemini.consultant.service;

import com.capgemini.consultant.VO.ResponseTemplateVO;
import com.capgemini.consultant.entity.Consultant;

import java.util.List;

public interface ConsultantServiceInterface {

    Consultant saveConsultant(Consultant consultant);

    Consultant findConsultantById(Long consultantId);

    void deleteConsultantById(Long consultantId);

    Consultant updateConsultant(Consultant consultant);

    ResponseTemplateVO getConsultantWithTeamAndEngagements(Long consultantId);

    List<ResponseTemplateVO> getConsultantsWithTeamAndEngagements();
}

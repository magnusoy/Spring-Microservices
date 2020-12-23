package com.capgemini.consultant.service;

import com.capgemini.consultant.VO.Engagement;
import com.capgemini.consultant.VO.ResponseTemplateVO;
import com.capgemini.consultant.VO.Team;
import com.capgemini.consultant.entity.Consultant;
import com.capgemini.consultant.repository.ConsultantRepository;
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
public class ConsultantService implements ConsultantServiceInterface {

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    RestTemplate restTemplate;

    public Consultant saveConsultant(Consultant consultant) {
        log.info("Inside saveConsultant method of ConsultantService");
        return consultantRepository.save(consultant);
    }

    public Consultant findConsultantById(Long consultantId) {
        log.info("Inside findConsultantById method of ConsultantService");
        return consultantRepository.findByConsultantId(consultantId);
    }

    public void deleteConsultantById(Long consultantId) {
        log.info("Inside deleteConsultantById method of ConsultantService");
        consultantRepository.deleteById(consultantId);
    }

    public Consultant updateConsultant(Consultant consultant) {
        log.info("Inside updateConsultant method of ConsultantService");
        return consultantRepository.save(consultant);
    }

    @Override
    public ResponseTemplateVO getConsultantWithTeamAndEngagements(Long consultantId) {
        log.info("Inside getConsultantWithTeamAndEngagements method of ConsultantService");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Team team = null;
        ResponseEntity<Engagement[]> engagements;
        Consultant consultant = consultantRepository.findByConsultantId(consultantId);
        if (consultant == null) {
            return null;
        }
        if (isObjectPresent(consultant.getTeamId())) {
            team = restTemplate.getForObject(
                    "http://TEAM-SERVICE/teams/" + consultant.getTeamId(),
                    Team.class);
        }
        if (!consultant.getEngagements().isEmpty()) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://ENGAGEMENT-SERVICE/engagements/");
            builder.queryParam("ids", String.join(",", (CharSequence) consultant.getEngagements()));
            URI uri = builder.build().encode().toUri();
            engagements = restTemplate.getForEntity(uri,
                    Engagement[].class);
            responseTemplateVO.setEngagements(Arrays.asList(Objects.requireNonNull(engagements.getBody())));
        } else {
            responseTemplateVO.setEngagements(new ArrayList());
        }
        responseTemplateVO.setConsultant(consultant);
        responseTemplateVO.setTeam(team);
        return responseTemplateVO;
    }

    @Override
    public List<ResponseTemplateVO> getConsultantsWithTeamAndEngagements() {
        log.info("Inside getConsultantsWithTeamAndEngagements method of ConsultantService");
        List<ResponseTemplateVO> responseTemplateVOS;
        responseTemplateVOS = consultantRepository.findAll()
                .stream()
                .map(consultant -> getConsultantWithTeamAndEngagements(consultant.getConsultantId()))
                .collect(Collectors.toList());
        return responseTemplateVOS;
    }

    private boolean isObjectPresent(Long id) {
        return id != null;
    }
}

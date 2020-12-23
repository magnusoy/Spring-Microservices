package com.capgemini.consultant.controller;

import com.capgemini.consultant.VO.ResponseTemplateVO;
import com.capgemini.consultant.entity.Consultant;
import com.capgemini.consultant.service.ConsultantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultants")
@Slf4j
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseTemplateVO>> findAllConsultants() {
        log.info("Inside findConsultantById method of ConsultantController");

        List<ResponseTemplateVO> responseTemplateVOS = consultantService.getConsultantsWithTeamAndEngagements();
        if (responseTemplateVOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseTemplateVOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> findConsultantById(@PathVariable("id") Long consultantId) {
        log.info("Inside findConsultantById method of ConsultantController");
        ResponseTemplateVO responseTemplateVO = consultantService.getConsultantWithTeamAndEngagements(consultantId);
        if (responseTemplateVO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseTemplateVO);
    }

    @PostMapping("/")
    public ResponseEntity<Consultant> saveConsultant(@Valid @RequestBody Consultant consultant) {
        log.info("Inside saveConsultant method of ConsultantController");
        consultantService.saveConsultant(consultant);
        URI uri = URI.create("/consultants/" + consultant.getConsultantId());
        return ResponseEntity.created(uri).body(consultant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultant> updateConsultant(@PathVariable("id") Long id, @Valid @RequestBody Consultant consultant) {
        if (consultantService.findConsultantById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Consultant updatedConsultant = consultantService.updateConsultant(consultant);
        URI uri = URI.create("/consultants/" + updatedConsultant.getConsultantId());
        return ResponseEntity.created(uri).body(updatedConsultant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConsultantById(@PathVariable("id") Long consultantId) {
        if (consultantService.findConsultantById(consultantId) == null) {
            return ResponseEntity.notFound().build();
        }
        consultantService.deleteConsultantById(consultantId);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

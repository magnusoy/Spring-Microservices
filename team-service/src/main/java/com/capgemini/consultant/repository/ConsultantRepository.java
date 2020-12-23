package com.capgemini.consultant.repository;

import com.capgemini.consultant.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    Consultant findByConsultantId(Long consultantId);
}

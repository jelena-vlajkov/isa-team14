package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.AnswerToComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerToComplaint, Long> {
}

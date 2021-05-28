package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    @Query(value = "SELECT c FROM Complaint c WHERE c.isAnswered = false")
    List<Complaint> getAllUnansweredComplaints();

    @Query(value = "SELECT c FROM Complaint c WHERE c.isAnswered = false AND c.role = ?1")
    List<Complaint> getAllUnansweredComplaintsDermatologists(String role);

}

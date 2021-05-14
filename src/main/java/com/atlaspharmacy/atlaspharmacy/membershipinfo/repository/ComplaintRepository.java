package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
}

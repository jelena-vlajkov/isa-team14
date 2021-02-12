package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class AnswerToComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Complaint complaint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private SystemAdmin systemAdmin;

    private String answer;

    public AnswerToComplaint() {
    }

    public AnswerToComplaint(Long id, Complaint complaint, SystemAdmin systemAdmin, String answer) {
        this.id = id;
        this.complaint = complaint;
        this.systemAdmin = systemAdmin;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SystemAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(SystemAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
    }
}

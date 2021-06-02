package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class VacationRequestAnswerDTO {
    private VacationRequestDTO vacationRequest;
    private boolean accepted;
    private String explanation;

    public VacationRequestAnswerDTO(VacationRequestDTO vacationRequest, boolean accepted, String explanation) {
        this.vacationRequest = vacationRequest;
        this.accepted = accepted;
        this.explanation = explanation;
    }

    public VacationRequestDTO getVacationRequest() {
        return vacationRequest;
    }

    public void setVacationRequest(VacationRequestDTO vacationRequest) {
        this.vacationRequest = vacationRequest;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

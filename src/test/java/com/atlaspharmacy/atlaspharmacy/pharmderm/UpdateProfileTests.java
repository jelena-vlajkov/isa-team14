package com.atlaspharmacy.atlaspharmacy.pharmderm;

import static org.assertj.core.api.Assertions.assertThat;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpdateProfileTests {

    @Test
    void testAddingVacationRequest() {
        VacationRequest vacationRequest = new VacationRequest();
        List<VacationRequest> vacationRequestList = new ArrayList<>();

        assertThat(vacationRequestList).hasSize(1);
    }

    @Test
    void testUpdatingName() {
        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setName("Jelenica");

        verify(dermatologist.getName().equals("Jelenica"));
    }

}

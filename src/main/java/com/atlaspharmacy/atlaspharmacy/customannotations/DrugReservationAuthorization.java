package com.atlaspharmacy.atlaspharmacy.customannotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('DERMATOLOGIST')")
public @interface DrugReservationAuthorization {
}

package com.atlaspharmacy.atlaspharmacy.customannotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('DERMATOLOGIST') || hasRole('PHARMACIST') || hasRole('PHARMACYADMIN')")
public @interface EmployeeAuthorization {
}

package com.atlaspharmacy.atlaspharmacy.customannotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('SUPPLIER') || hasRole('PH')")
public @interface OrderAuthorization {
}

package com.atlaspharmacy.atlaspharmacy.customannotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('PHARMACIST') || hasRole('PHARMACYADMIN')")
public @interface StorageAuthorization {
}


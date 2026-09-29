package com.fcv.citas.api.domain.model;

import java.util.Set;

public final class User {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String documentType;
    private final String documentNumber;
    private final String email;
    private final String phone;
    private final String passwordHash;
    private final boolean active;
    private final Set<String> roles;

    public User(
            Long id,
            String firstName,
            String lastName,
            String documentType,
            String documentNumber,
            String email,
            String phone,
            String passwordHash,
            boolean active,
            Set<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.active = active;
        this.roles = Set.copyOf(roles);
    }

    public static User newRegistration(
            String firstName,
            String lastName,
            String documentType,
            String documentNumber,
            String email,
            String phone,
            String passwordHash) {
        return new User(
                null,
                firstName,
                lastName,
                documentType,
                documentNumber,
                email,
                phone,
                passwordHash,
                true,
                Set.of("USER"));
    }

    public User withId(Long assignedId) {
        return new User(
                assignedId, firstName, lastName, documentType, documentNumber, email, phone, passwordHash, active,
                roles);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isActive() {
        return active;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

package de.seuhd.campuscoffee.domain.model;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
public record User (
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String loginName,
        String emailAddress,
        String firstName,
        String lastName
        //TODO: Implement user domain object
) implements Serializable { // serializable to allow cloning (see TestFixtures class).
    @Serial
    private static final long serialVersionUID = 1L;
    public User {
        Objects.requireNonNull(createdAt, "createdAt must not be null");
        Objects.requireNonNull(updatedAt, "updatedAt must not be null");
        Objects.requireNonNull(loginName, "loginName must not be null");
        Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
    }


}

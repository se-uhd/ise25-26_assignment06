package de.campuscoffee.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain-Modell User.
 * Null-Checks werden in statischen Fabrikmethoden/Settern durchgeführt.
 */
public class User {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String login;
    private String email;
    private String firstName;
    private String lastName;

    public User() {}

    private User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String login, String email, String firstName, String lastName) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        validateNonNullFields();
    }

    private void validateNonNullFields() {
        Objects.requireNonNull(login, "login darf nicht null sein");
        Objects.requireNonNull(email, "email darf nicht null sein");
        Objects.requireNonNull(firstName, "firstName darf nicht null sein");
        Objects.requireNonNull(lastName, "lastName darf nicht null sein");
    }

    public static User createNew(String login, String email, String firstName, String lastName) {
        Objects.requireNonNull(login, "login darf nicht null sein");
        Objects.requireNonNull(email, "email darf nicht null sein");
        Objects.requireNonNull(firstName, "firstName darf nicht null sein");
        Objects.requireNonNull(lastName, "lastName darf nicht null sein");
        LocalDateTime now = LocalDateTime.now();
        return new User(null, now, now, login, email, firstName, lastName);
    }

    public User withId(Long id) {
        this.id = id;
        return this;
    }

    public void touchForUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User copyWithUpdateFrom(User other) {
        Objects.requireNonNull(other, "other darf nicht null sein");
        if (other.login != null) this.login = other.login;
        if (other.email != null) this.email = other.email;
        if (other.firstName != null) this.firstName = other.firstName;
        if (other.lastName != null) this.lastName = other.lastName;
        this.updatedAt = LocalDateTime.now();
        validateNonNullFields();
        return this;
    }

    // --- Getter / Setter ---
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLogin(String login) {
        this.login = Objects.requireNonNull(login, "login darf nicht null sein");
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "email darf nicht null sein");
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName darf nicht null sein");
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName darf nicht null sein");
    }
}
package model;

import java.time.LocalDateTime;

import model.enums.UserRole;

public class User {
    private String email;
    private UserRole role;
    private LocalDateTime creationDate;
    private boolean isActive;

    public User(String email) {
        this.email = email;
        this.role = UserRole.STANDARD_USER;
        this.creationDate = LocalDateTime.now();
        this.isActive = true;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

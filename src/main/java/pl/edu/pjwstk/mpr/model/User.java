package pl.edu.pjwstk.mpr.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import pl.edu.pjwstk.mpr.model.enums.UserRole;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;
    private LocalDateTime creationDate;
    @Column(name = "is_active")
    private Boolean active;

    public User() {
    }

    public User(String email) {
        this.email = email;
        this.role = UserRole.STANDARD_USER;
        this.creationDate = LocalDateTime.now();
        this.active = true;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        active = active;
    }
}

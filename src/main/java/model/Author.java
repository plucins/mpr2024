package model;

import java.time.LocalDate;

public class Author {
    private String firstName;
    private String lastName;
    private LocalDate dayOfBirth;

    public Author(String firstName, String lastName, LocalDate dayOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }
}

package pl.edu.pjwstk.mpr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pjwstk.mpr.model.User;
import pl.edu.pjwstk.mpr.model.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(UserRole userRole);

}

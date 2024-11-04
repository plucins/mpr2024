package pl.edu.pjwstk.mpr.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pjwstk.mpr.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

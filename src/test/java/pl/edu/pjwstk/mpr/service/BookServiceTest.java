package pl.edu.pjwstk.mpr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.edu.pjwstk.mpr.model.Book;
import pl.edu.pjwstk.mpr.model.enums.BookType;
import pl.edu.pjwstk.mpr.repository.BookRepository;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createBook_shouldCreateBook_WhenProvidedCorrectData() {
        //Given
        Book b = new Book("Title", new ArrayList<>(), 123L, BookType.ACTION);
        when(bookRepository.save(any())).thenReturn(b);

        //When
        Book createdBook = bookService.createBook(b);

        //Then
        assertNotNull(createdBook);
        verify(bookRepository, times(1)).save(b);
    }

    @Test
    void createBook_shouldThrowException_WhenBookCreatedWithoutISBN() {
        //Given
        Book b = new Book();
        when(bookRepository.existsById(any())).thenReturn(false);

        //When //Then
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(b));


    }

    @Test
    void getBookById_shouldGetBookById_WhenProvideCorrectData() {
        //Given
        Optional<Book> b = Optional.of(new Book());
        when(bookRepository.existsById(any())).thenReturn(true);
        when(bookRepository.findById(any())).thenReturn(b);

        //When
        Book actualBook = bookService.getBookById(1L);

        //Then
        assertEquals(actualBook, b.get());
        verify(bookRepository, times(1)).existsById(any());
        verify(bookRepository, atLeast(1)).findById(any());
    }

    @Test
    void getBookById_shouldThrowException_WhenTryingFindBookByIdWhichNotExist() {
        //Given
        Optional<Book> b = Optional.of(new Book());
        when(bookRepository.existsById(any())).thenReturn(false);

        //When //Then
        assertThrows(IllegalArgumentException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void updateBook_shouldUpdateBook_WhenPassedCorrectInput() {
        //Given
        Book actualBook = new Book("Test", new ArrayList<>(), 1L, BookType.ACTION);
        Book updatedBook = new Book("Test", new ArrayList<>(), 1L, BookType.HORROR);
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.findById(any())).thenReturn(Optional.of(actualBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Then
        actualBook = bookService.updateBook(1L, updatedBook);

        //Then
        assertNotNull(actualBook);
        assertEquals(BookType.HORROR, actualBook.getBookType());
    }

    @Test
    void updateBook_shouldNotUpdateBook_WhenUpdatedBookTypeIsNull() {
        //Given
        Book actualBook = new Book("Test", new ArrayList<>(), 1L, BookType.ACTION);
        Book updatedBook = new Book("Test", new ArrayList<>(), 1L, null);
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.findById(any())).thenReturn(Optional.of(actualBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Then
        actualBook = bookService.updateBook(1L, updatedBook);

        //Then
        assertNotNull(actualBook);
        assertEquals(BookType.ACTION, actualBook.getBookType());
    }

    @Test
    void deleteBook_shouldDeleteBook_WhenDeletedBookExists() {
        //Given
        when(bookRepository.existsById(anyLong())).thenReturn(true);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(new Book()));

        //When
        bookService.deleteBook(1L);

        //Then
        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteBook_shouldThrowException_WhenTryingToDeleteBookWhichNotExists() {
        //Given
        when(bookRepository.existsById(anyLong())).thenReturn(false);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(new Book()));

        //When //Then
        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBook(1L));

    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks_WhenBooksExist() {
        //Given
        Book book1 = new Book("Test", new ArrayList<>(), 1L, BookType.HORROR);
        Book book2 = new Book("Test", new ArrayList<>(), 2L, BookType.ACTION);

        List<Book> mockBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(mockBooks);

        //When
        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(BookType.HORROR, result.get(0).getBookType());
        assertEquals(BookType.ACTION, result.get(1).getBookType());
        verify(bookRepository).findAll();
    }

    @Test
    void getAllBooks_ShouldReturnEmptyList_WhenNoBooksExist() {
        // Given
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookRepository).findAll();
    }
}
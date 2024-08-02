package com.LMSystem.Sara.Mohamed_Library.Management.System.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.Book;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.BookRepository;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Services.BookService;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        bookService.getAllBooks();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setPublicationYear(1949);
        book.setIsbn("9780451524935");
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookById(id);
        assertTrue(foundBook.isPresent());
        assertEquals(book, foundBook.get());
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setPublicationYear(1949);
        book.setIsbn("9780451524935");
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.addBook(book);
        assertEquals(book, savedBook);
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setTitle("1984");
        existingBook.setAuthor("George Orwell");
        existingBook.setPublicationYear(1949);
        existingBook.setIsbn("9780451524935");

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPublicationYear(1950);
        updatedBook.setIsbn("9780451524936");

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.updateBook(id, updatedBook);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals(1950, result.getPublicationYear());
        assertEquals("9780451524936", result.getIsbn());
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setPublicationYear(1949);
        book.setIsbn("9780451524935");

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        bookService.deleteBook(id);

        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void testDeleteBookNotFound() {
        Long id = 1L;

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.deleteBook(id);
        });

        assertEquals("Book not found", exception.getMessage());
    }
}
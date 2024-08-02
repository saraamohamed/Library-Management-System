package com.LMSystem.Sara.Mohamed_Library.Management.System.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.Book;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.BorrowingRecord;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.Patron;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.BookRepository;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.BorrowingRepository;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.PatronRepository;



@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());
        borrowingRecordRepository.save(record);
    }

    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        if (!record.getBook().getId().equals(bookId) || !record.getPatron().getId().equals(patronId)) {
            throw new RuntimeException("Invalid record");
        }
        record.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(record);
    }
}

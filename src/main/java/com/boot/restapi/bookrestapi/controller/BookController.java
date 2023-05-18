package com.boot.restapi.bookrestapi.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.MySQL55Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.health.HealthProperties.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.restapi.bookrestapi.entities.Book;
import com.boot.restapi.bookrestapi.services.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bs;

	// @RequestMapping(value = "/books", method = RequestMethod.GET)
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> bookList = bs.getAllBooks();
		if (bookList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(bookList);
	}
	/*
	 * Previously this code is written(not maintaining status code)
	 * 
	 * @GetMapping("/books/{id}")
	 * public Book getBook(@PathVariable("id") int id) {
	 * return bs.getBookById(id);
	 * }
	 */

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bs.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book b) {
		Book b1 = null;
		try {
			b1 = this.bs.addBook(b);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		try {
			this.bs.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
		try {
			this.bs.updateBook(book, id);
			return ResponseEntity.ok().body(book);// returning status as well as data too
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}

package com.boot.restapi.bookrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Book> getBooks() {
		return bs.getAllBooks();
	}

	@GetMapping("/books/{id}")
	public Book getBook(@PathVariable("id") int id) {
		return bs.getBookById(id);
	}

	@PostMapping("/books")
	public Book addBook(@RequestBody Book b) {
		Book b1 = this.bs.addBook(b);
		return b1;
	}

	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable int id) {
		bs.deleteBook(id);
	}

	@PutMapping("/books/{id}")
	public void updateBook(@RequestBody Book book, @PathVariable int id) {
		bs.updateBook(book, id);
	}
}

package com.boot.restapi.bookrestapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.boot.restapi.bookrestapi.entities.Book;

@Component
public class BookService {

	private static List<Book> books = new ArrayList<>();

	static {
		books.add(new Book(1, "Java", "Sun"));
		books.add(new Book(2, "Python", "Moon"));
		books.add(new Book(3, "Golang", "Saturn"));
	}

	// get all book
	public List<Book> getAllBooks() {
		return books;
	}

	// get single book by id
	public Book getBookById(int id) {
		Book b = null;
		try {
			b = books.stream().filter(e -> e.getId() == id).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public Book addBook(Book b) {
		books.add(b);
		return b;
	}

	public void deleteBook(int bid) {
		books = books.stream().filter(e -> e.getId() != bid).collect(Collectors.toList());
	}

	public void updateBook(Book book, int bid) {
		books.stream().map(b -> {
			if (b.getId() == bid) {
				b.setAuthor(book.getAuthor());
				b.setName(book.getName());

			}
			return b;
		}).collect(Collectors.toList());
	}

}
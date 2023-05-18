package com.boot.restapi.bookrestapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.restapi.bookrestapi.dao.BookRepository;
import com.boot.restapi.bookrestapi.entities.Book;

@Component
public class BookService {

	// private static List<Book> books = new ArrayList<>();

	// static {
	// books.add(new Book(1, "Java", "Sun"));
	// books.add(new Book(2, "Python", "Moon"));
	// books.add(new Book(3, "Golang", "Saturn"));
	// }
	@Autowired
	BookRepository bookRepository;

	// get all book
	public List<Book> getAllBooks() {
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}

	// get single book by id
	public Book getBookById(int id) {
		Book b = null;
		try {
			// b = books.stream().filter(e -> e.getId() == id).findFirst().get();
			b = this.bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public Book addBook(Book b) {
		// books.add(b);
		Book result = this.bookRepository.save(b);
		return result;
	}

	public void deleteBook(int bid) {
		// books = books.stream().filter(e -> e.getId() !=
		// bid).collect(Collectors.toList());
		this.bookRepository.deleteById(bid);
	}

	public void updateBook(Book book, int bid) {
		// books.stream().map(b -> {
		// if (b.getId() == bid) {
		// b.setAuthor(book.getAuthor());
		// b.setName(book.getName());

		// }
		// return b;
		// }).collect(Collectors.toList());
		book.setId(bid);
		bookRepository.save(book);
	}

}
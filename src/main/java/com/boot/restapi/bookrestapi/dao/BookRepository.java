package com.boot.restapi.bookrestapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.boot.restapi.bookrestapi.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	Book findById(int id);

}

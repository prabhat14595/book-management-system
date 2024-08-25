package com.example.book_management_system.repository;

import com.example.book_management_system.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}

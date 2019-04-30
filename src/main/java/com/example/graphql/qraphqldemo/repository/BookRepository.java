package com.example.graphql.qraphqldemo.repository;

import com.example.graphql.qraphqldemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

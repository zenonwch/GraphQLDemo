package com.example.graphql.qraphqldemo.repository;

import com.example.graphql.qraphqldemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

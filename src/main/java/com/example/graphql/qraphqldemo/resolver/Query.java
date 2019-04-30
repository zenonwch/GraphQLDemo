package com.example.graphql.qraphqldemo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.qraphqldemo.model.Author;
import com.example.graphql.qraphqldemo.model.Book;
import com.example.graphql.qraphqldemo.repository.AuthorRepository;
import com.example.graphql.qraphqldemo.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public Query(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }
}

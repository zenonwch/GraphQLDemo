package com.example.graphql.qraphqldemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.qraphqldemo.model.Author;
import com.example.graphql.qraphqldemo.model.Book;
import com.example.graphql.qraphqldemo.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private final AuthorRepository authorRepository;

    public BookResolver(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(final Book book) {
        return authorRepository.findById(book.getAuthor().getId()).orElse(null);
    }
}

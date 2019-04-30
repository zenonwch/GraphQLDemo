package com.example.graphql.qraphqldemo.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.qraphqldemo.exception.BookNotFoundException;
import com.example.graphql.qraphqldemo.model.Author;
import com.example.graphql.qraphqldemo.model.Book;
import com.example.graphql.qraphqldemo.repository.AuthorRepository;
import com.example.graphql.qraphqldemo.repository.BookRepository;

import javax.validation.constraints.NotNull;

public class Mutation implements GraphQLMutationResolver {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public Mutation(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(final String firstName, final String lastName) {
        final Author author = new Author(firstName, lastName);
        return authorRepository.save(author);
    }

    public Book newBook(final String title, final String isbn, final Integer pageCount, final Long authorId) {
        final int unboxedPageCount = pageCount == null ? 0 : pageCount;
        final Book book = new Book(title, isbn, unboxedPageCount, new Author(authorId));

        return bookRepository.save(book);
    }

    public boolean deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
        return true;
    }

    public Book updateBookPageCount(final @NotNull Integer pageCount, final Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("The book to be updated was not found", bookId));
        book.setPageCount(pageCount);

        return bookRepository.save(book);
    }
}

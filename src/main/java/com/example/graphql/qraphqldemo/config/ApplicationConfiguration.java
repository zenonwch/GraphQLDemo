package com.example.graphql.qraphqldemo.config;

import com.example.graphql.qraphqldemo.exception.GraphQLErrorAdapter;
import com.example.graphql.qraphqldemo.model.Author;
import com.example.graphql.qraphqldemo.model.Book;
import com.example.graphql.qraphqldemo.repository.AuthorRepository;
import com.example.graphql.qraphqldemo.repository.BookRepository;
import com.example.graphql.qraphqldemo.resolver.BookResolver;
import com.example.graphql.qraphqldemo.resolver.Mutation;
import com.example.graphql.qraphqldemo.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public GraphQLErrorHandler errorHandler() {
        return errors -> {
            final Predicate<GraphQLError> isClientError
                    = error -> !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);

            final Predicate<GraphQLError> notClientError = isClientError.negate();

            final List<GraphQLError> clientErrors = errors.stream()
                    .filter(isClientError)
                    .collect(Collectors.toList());

            final List<GraphQLError> serverErrors = errors.stream()
                    .filter(notClientError)
                    .map(GraphQLErrorAdapter::new)
                    .collect(Collectors.toList());

            final List<GraphQLError> e = new ArrayList<>();
            e.addAll(clientErrors);
            e.addAll(serverErrors);
            return e;
        };
    }

    @Bean
    public BookResolver authorResolver(final AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public Query query(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        return new Query(authorRepository, bookRepository);
    }

    @Bean
    public Mutation mutation(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        return new Mutation(authorRepository, bookRepository);
    }

    @Bean
    public CommandLineRunner demo(final AuthorRepository authorRepository,
                                  final BookRepository bookRepository) {
        return args -> {
            final Author author = new Author("Herbert", "Shildt");
            authorRepository.save(author);

            bookRepository.save(new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
        };
    }
}

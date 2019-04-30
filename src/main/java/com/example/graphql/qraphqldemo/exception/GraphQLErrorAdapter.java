package com.example.graphql.qraphqldemo.exception;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class GraphQLErrorAdapter implements GraphQLError {

    private final GraphQLError error;

    public GraphQLErrorAdapter(final GraphQLError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return (error instanceof ExceptionWhileDataFetching)
                ? ((ExceptionWhileDataFetching) error).getException().getMessage()
                : error.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return error.getErrorType();
    }
}

package com.handyman.backend.commons;

public interface UseCase<Input, Output> {
    Output execute(Input input);
}

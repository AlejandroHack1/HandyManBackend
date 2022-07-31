package com.handyman.backend.commons;

public interface MultipleUseCase<Input1, Input2, Output> {

    Output execute(Input1 input1, Input2 input2);

}

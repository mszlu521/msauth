package com.mszlu.msauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Option<T> {

    private T value;
    private String label;
    private List<Option<T>> children;
}
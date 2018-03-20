package com.github.zakyalvan.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDescriptor implements Serializable {
    private String fieldName;
    private String errorMessage;

    public ErrorDescriptor() {
    }

    public ErrorDescriptor(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
package com.tuan.exercise.projman.exception;

public class MissingRequestBodyField extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7259655528881844208L;

    public MissingRequestBodyField(String message) {
        super(message);
    }
}

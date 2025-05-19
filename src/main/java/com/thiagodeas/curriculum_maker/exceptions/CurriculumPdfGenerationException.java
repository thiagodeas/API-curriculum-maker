package com.thiagodeas.curriculum_maker.exceptions;

public class CurriculumPdfGenerationException extends RuntimeException{
    
    public CurriculumPdfGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
    public CurriculumPdfGenerationException(String message) {
        super(message);
    }
}

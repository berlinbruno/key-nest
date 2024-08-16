package dev.berlinbruno.key_nest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        // Log the exception details
        logger.error("An error occurred", ex);

        // Add error message to the model
        model.addAttribute("error", "An unexpected error occurred. Please try again later.");

        // Optionally, you can add specific details if needed
        model.addAttribute("errorMessage", ex.getMessage());

        // Return the error view
        return "error"; // Maps to error.html
    }
}

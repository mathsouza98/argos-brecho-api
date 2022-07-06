package com.brecho.argos.domain.sale.adapters.api.interceptors;

import com.brecho.argos.domain.sale.adapters.api.models.response.error.ErrorResponse;
import com.brecho.argos.domain.sale.core.exceptions.BuyerCannotBeSellerException;
import com.brecho.argos.domain.sale.core.exceptions.EmptyArgumentsException;
import com.brecho.argos.domain.sale.core.exceptions.InsufficientQuantityItemException;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import com.brecho.argos.domain.user.core.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InsufficientQuantityItemException.class})
    protected ResponseEntity<ErrorResponse> handleInsufficientQuantityItemException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({UnavailableItemException.class, UserNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleUnavailableItemException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BuyerCannotBeSellerException.class, EmptyArgumentsException.class})
    protected ResponseEntity<ErrorResponse> handleBuyerCannotBeSellerException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

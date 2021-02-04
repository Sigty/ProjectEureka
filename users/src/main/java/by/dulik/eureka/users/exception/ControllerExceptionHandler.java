package by.dulik.eureka.users.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<CustomUserApiException> feignExceptionHandler(FeignException exception) {
        HttpStatus httpStatusException = HttpStatus.resolve(exception.status()) == null
                ? HttpStatus.BAD_REQUEST
                : HttpStatus.resolve(exception.status());
        return new ResponseEntity<>(CustomUserApiException.builder()
                .status(httpStatusException)
                .timestamp(LocalDateTime.now())
                .message(exception.getLocalizedMessage() != null
                        ? exception.getLocalizedMessage()
                        : exception.getMessage())
                .errors(Collections.singletonList(exception.getMessage()))
                .build(),
                httpStatusException);
    }
}

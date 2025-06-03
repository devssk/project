package io.project.common.exception;

import io.project.interfaces.ResponseCode;
import io.project.interfaces.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ResponseDto(ResponseCode.FAIL, new ResponseBody(e.getMessage())));
    }

}

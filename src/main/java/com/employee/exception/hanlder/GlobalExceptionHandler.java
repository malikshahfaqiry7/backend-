package com.employee.exception.hanlder;

import com.employee.exception.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> npexception(NullPointerException exception) {
        return new ResponseEntity<>("I am here for handling null pointer exception", HttpStatus.INTERNAL_SERVER_ERROR);
        // 4xx any error code which mean client have some issue with the request
        // 5xx mean server itself has some problem while processing request
    }

    @ExceptionHandler(value = MyCustomException.class)
    public ResponseEntity<Object> npexception(MyCustomException exception) {

        Map<String,String> map =new HashMap<>();
        map.put("name","Rabin");
        map.put("msg",exception.getMessage());
        map.put("reason", Arrays.stream(exception.getStackTrace()).map(x->x.toString()).collect(Collectors.joining()));
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        // 4xx any error code which mean client have some issue with the request
        // 5xx mean server itself has some problem while processing request
    }


    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> exception(Throwable exception) {
        return new ResponseEntity<>("I am global handler"+exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        // 4xx any error code which mean client have some issue with the request
        // 5xx mean server itself has some problem while processing request
    }
}

package kr.smartfactory.platform.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * @description : Null 값을 return 하는 경우의 예외처리 
 *
 * @author : Kyunghun Park 
 * @since : 2021. 10. 18. 오전 11:09:11
 *
 */
@RestControllerAdvice
public class CustomException {
   

    @ExceptionHandler(value = NullValueObjectException.class)
    public ResponseEntity<Object> notFoundException(NullValueObjectException exception) {
        
        return new ResponseEntity<>("Object Value is Null", HttpStatus.OK);
    }
    
    
}

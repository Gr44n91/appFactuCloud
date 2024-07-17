package com.FactuCloud.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceNotFoundException extends RuntimeException{

    public static String MESSAGE = "Resource not found in table: %s";
    //Constructor padre
    public ResourceNotFoundException(String tableName){
        super(String.format(MESSAGE, tableName));
        log.info("Resource not found in table: " + tableName);
    }

    public String getMessage(){
        return super.getMessage();
    }
}

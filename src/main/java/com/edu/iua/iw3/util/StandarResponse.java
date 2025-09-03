package com.edu.iua.iw3.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandarResponse {
    private String message;
    @JsonIgnore
    private Throwable ex;
    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonIgnore
    private boolean devInfoEnabled;

    public int getCode() {
        return httpStatus.value();
    }
    
    public String getDevInfo() {
        if (devInfoEnabled) {
            if (ex != null) {
                return ExceptionUtils.getStackTrace(ex);                
            }else{
                return "No stack trace";
            }
            
        } else {
            return null;
        }
    }
    
    public String getMessage(){
        if (message != null) {
            return message;
        } else {
            return httpStatus.getReasonPhrase();
        }
    }
}

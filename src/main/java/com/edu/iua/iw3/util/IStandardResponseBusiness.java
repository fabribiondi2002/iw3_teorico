package com.edu.iua.iw3.util;

import org.springframework.http.HttpStatus;

public interface IStandardResponseBusiness {
    public StandarResponse build(HttpStatus httpStatus, Throwable ex, String message);
}

package com.edu.iua.iw3.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
public class StandardResponseBusiness implements IStandardResponseBusiness {
    @Value("${dev.info.enabled:false}")
    private boolean devInfoEnabled;
    @Override

    public StandarResponse build(HttpStatus httpStatus, Throwable ex, String message) {
        StandarResponse response = new StandarResponse();
        response.setHttpStatus(httpStatus);
        response.setDevInfoEnabled(devInfoEnabled);
        response.setEx(ex);
        response.setMessage(message);
        return response;
    }

}

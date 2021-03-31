package com.aliction.springcolorsserver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ColorNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 8459050238510267155L;

    public ColorNotFoundException(String message) {
        super(message);
    }
}

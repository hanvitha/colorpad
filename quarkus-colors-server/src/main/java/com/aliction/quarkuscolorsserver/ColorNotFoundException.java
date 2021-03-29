package com.aliction.quarkuscolorsserver;

import org.jboss.resteasy.annotations.ResponseObject;

@ResponseObject
public class ColorNotFoundException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ColorNotFoundException(String message){
        super(message);
    }
    

}

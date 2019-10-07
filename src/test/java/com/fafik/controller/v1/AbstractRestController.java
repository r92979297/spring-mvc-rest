package com.fafik.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController {

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e ){
            throw new RuntimeException(e);
        }
    }
}

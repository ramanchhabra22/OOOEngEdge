package com.oooeng.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oooeng.exception.ResponseStatus;


import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response implements Serializable {
    public static final long serialVersionUID = 1L;


    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Response() {}

    public Response(ResponseStatus error) {
        this.code = error.value();
        this.message = error.getReasonPhrase();
    }

    public Response(ResponseStatus error, Object data) {
        this.code = error.value();
        this.message = error.getReasonPhrase();
        this.data =  data;
    }

    public Response(String errorEessage) {
        this.message = errorEessage;
    }

}

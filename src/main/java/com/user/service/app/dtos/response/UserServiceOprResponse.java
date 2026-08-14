package com.user.service.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserServiceOprResponse {
    private String statusCode;
    private String responseMsg;
    private Boolean isOprSuccess;

    public UserServiceOprResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public UserServiceOprResponse setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public UserServiceOprResponse setIsOprSuccess(Boolean isOprSuccess) {
        this.isOprSuccess = isOprSuccess;
        return this;
    }

    public static UserServiceOprResponse createResponse(){
        return new UserServiceOprResponse();
    }
}

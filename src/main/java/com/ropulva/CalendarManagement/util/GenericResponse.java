package com.ropulva.CalendarManagement.util;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.sql.Timestamp;


@Getter
@Setter
public class GenericResponse {
    private String responseCode;
    private String responseMessage;
    private String  responseStatus;
    @JsonIgnore
    private HttpStatus httpStatus;
    private Timestamp timestamp;



    public void setSuccessful(){
        this.responseCode = "0";
        this.responseMessage = "Successful";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Success";
        this.timestamp = DateTimeFormatterUtil.getCurrentTimestamp();
    }

    public void setServerErrorHappened(){
        this.responseCode = "-1";
        this.responseMessage = "Something wrong happened within our server.";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.responseStatus = "Failed";
        this.timestamp = DateTimeFormatterUtil.getCurrentTimestamp();
    }

    public void setEventDoesNotExist() {
        this.responseCode = "1";
        this.responseMessage = "This Event Does Not Exist";
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        this.responseStatus = "Failed";
        this.timestamp = DateTimeFormatterUtil.getCurrentTimestamp();
    }
}

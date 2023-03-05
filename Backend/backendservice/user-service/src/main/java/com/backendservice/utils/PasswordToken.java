package com.backendservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordToken {
    private static int EXPIRATION_TIME = 10;
    private static Date calculateExpirationTime(int time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, time);
        return new Date(calendar.getTime().getTime());
    }
    private String token;
    private Date expirationTime;
    public PasswordToken(String token){
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }
}
package com.backend.realestatebackend.fields.TokenFields;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    private static int EXPIRATION_TIME = 10;

    private static Date calculateExpirationTime(int time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, time);
        return new Date(calendar.getTime().getTime());
    }

    private String token;
    private Date expirationTime;

    public VerificationToken(String token){
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }
}
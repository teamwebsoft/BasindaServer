package com.basinda.utils;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.util.MultiValueMap;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SendOTPUtil {
    private final String AUTH_TOKEN = "";
    private final String ACCOUNT_STD = "";
    private final String FROM_NUMBER = "+8801315656967";

    /*public void send(String phone) throws ParseException {
        Twilio.init(ACCOUNT_STD, AUTH_TOKEN);
        int min = 100000;
        int max = 999999;
        int number = (int)(Math.random()*(max-min+1)+min);

        String msg = "Your OTP = "+number+" please verify this OTP in your Application by Teamwebsoft.com";
        Message message = Message.creator(new PhoneNumber(phone),new PhoneNumber(FROM_NUMBER), msg).create();

        Date myDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = simpleDateFormat.parse(myDate.toString());
        long millis = date.getTime();
    }*/

    public void receive(MultiValueMap<String, String> smsCallBack){

    }
}
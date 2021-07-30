package com.codecafe.grpc.protobuf;

import com.codecafe.grpc.protobuf.model.Credentials;
import com.codecafe.grpc.protobuf.model.EmailCredentials;
import com.codecafe.grpc.protobuf.model.PhoneOTP;

public class OneOfDemo {

    public static void main(String[] args) {

        EmailCredentials emailCredentials = EmailCredentials.newBuilder()
                .setEmail("abc@xyz.com")
                .setPassword("1234")
                .build();

        PhoneOTP phoneOTP = PhoneOTP.newBuilder()
                .setPhoneNumber(1231231234)
                .setCode(8080)
                .build();

        Credentials credentials = Credentials.newBuilder()
                .setEmailCredentials(emailCredentials)
                .build();

        login(credentials);

        // when both candidates of one of are provided
        // then only second one will be taken
        // previous one will be erased

        credentials = Credentials.newBuilder()
                .setEmailCredentials(emailCredentials)
                .setPhoneOTP(phoneOTP)
                .build();

        login(credentials);

    }

    public static void login(Credentials credentials) {

        // automatically creates an ENUM for us
        switch (credentials.getCredentialsCase()) {
            case EMAILCREDENTIALS:
                System.out.println("Email login");
                System.out.println(credentials);
                break;
            case PHONEOTP:
                System.out.println("OTP login");
                System.out.println(credentials);
                break;
        }

    }

}
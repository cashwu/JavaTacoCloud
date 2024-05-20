package com.cashwu.javatacocloud.dto;

import com.cashwu.javatacocloud.model.MyUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public MyUser toUser(PasswordEncoder passwordEncoder) {
        return new MyUser(username,
                          passwordEncoder.encode(password),
                          fullname,
                          street,
                          city,
                          state,
                          zip,
                          phone);
    }
}

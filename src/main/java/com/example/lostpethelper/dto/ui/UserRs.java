package com.example.lostpethelper.dto.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRs {
    private String name;
    private String lastname;
    private String phoneNumber;
    private String email;
}

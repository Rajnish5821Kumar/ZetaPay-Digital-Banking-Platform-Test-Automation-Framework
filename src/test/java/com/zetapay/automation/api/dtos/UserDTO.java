package com.zetapay.automation.api.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String panNumber;
}

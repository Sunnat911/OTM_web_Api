package com.example.OTM_web_api.dto.receiveModels;

import com.example.OTM_web_api.entity.Enum.roleEnum.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpReceiveModel {

    @JsonProperty("first_name")
    @NotEmpty(message = "ism familya bo'sh bo'lmasligi kerak")
    private String firstName;

    @JsonProperty("userName")
    @NotEmpty(message = "username bo'sh bo'lmasligi kerak")
    private String userName;

    @NotEmpty(message = "password bo'sh bo'lmasligi kerak")
    private String password;


    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("user_role")
    private RoleEnum roleEnum;

    @NotNull
    private Long univerId;

}

package com.example.OTM_web_api.dto.receiveModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddToUniverReceiveModel {

    private Long univerId;

    private String userName;

    private String passwordNumber;

}

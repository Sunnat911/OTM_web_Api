package com.example.OTM_web_api.service.baseService;

import com.example.OTM_web_api.dto.apiResponse.ApiResponse;

public interface BaseService {
    ApiResponse SUCCESS = new ApiResponse("SUCCESS",true,0);
    ApiResponse USER_EXIST = new ApiResponse("this user name already has",false,-100);
    ApiResponse USER_NOT_FOUND = new ApiResponse("user not found",false,-100);
    ApiResponse UNIVER_EXIST = new ApiResponse("this univer already has in DB",false,-100);
}

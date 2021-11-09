package com.example.OTM_web_api.controller;

import com.example.OTM_web_api.config.CurrentUser;
import com.example.OTM_web_api.dto.receiveModels.ArizaDTO;
import com.example.OTM_web_api.dto.apiResponse.ApiResponse;
import com.example.OTM_web_api.entity.Enum.univerEnum.ArizaStateEnum;
import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.example.OTM_web_api.service.arizaService.ArizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otm/ariza")
public class ArizaController {
    @Autowired
    ArizaService arizaService;

    @PostMapping("/add")
    public HttpEntity<?> arizaAdd(@RequestBody ArizaDTO arizaDTO, @CurrentUser UserEntity  currentUser){
        ApiResponse apiResponse = arizaService.arizaAdd(arizaDTO, currentUser);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/changeStatus/{arizaId}")
    public HttpEntity<?> changeStatus(@PathVariable Long arizaId, @RequestParam ArizaStateEnum status){
        ApiResponse apiResponse=arizaService.changeStatus(arizaId,status);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);

    }
}

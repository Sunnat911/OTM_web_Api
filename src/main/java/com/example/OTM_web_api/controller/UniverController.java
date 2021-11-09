package com.example.OTM_web_api.controller;

import com.example.OTM_web_api.dto.apiResponse.ApiResponse;
import com.example.OTM_web_api.service.univerService.UniverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/otm/univer")
public class UniverController {

   private final UniverService univerService;

   @Autowired
    public UniverController(UniverService univerService) {
        this.univerService = univerService;
    }

    @PreAuthorize("hasAuthority('ADMIN_OTM')")
    @PostMapping("/add/{univerName}")
    public ResponseEntity<?> addUniver(@Valid @PathVariable String univerName){
        ApiResponse apiResponse = univerService.addUniver(univerName);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    }


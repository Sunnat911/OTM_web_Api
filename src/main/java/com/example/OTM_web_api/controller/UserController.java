package com.example.OTM_web_api.controller;

import com.example.OTM_web_api.dto.receiveModels.UserAddToUniverReceiveModel;
import com.example.OTM_web_api.dto.receiveModels.UserSignInReceiveModel;
import com.example.OTM_web_api.dto.receiveModels.UserSignUpReceiveModel;
import com.example.OTM_web_api.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/otm/user")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get") // test url
    public String blabla(){
        return "Hello";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid  @RequestBody UserSignUpReceiveModel userSignUpReceiveModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSignUpReceiveModel));
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody UserSignInReceiveModel userSignInModel){
        return ResponseEntity.ok(userService.login(userSignInModel));
    }

    @PostMapping ("/add/user/toUniver/")
    public ResponseEntity addUserToUniver (@RequestBody UserAddToUniverReceiveModel userAddToUniverReceiveModel) {
        return ResponseEntity.ok(userService.addUserToUniver(userAddToUniverReceiveModel));
    }
    



}

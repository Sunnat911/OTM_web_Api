package com.example.OTM_web_api.service.userService;

import com.example.OTM_web_api.dto.apiResponse.ApiResponse;
import com.example.OTM_web_api.dto.receiveModels.SubmitArizaReceiveModel;
import com.example.OTM_web_api.dto.receiveModels.UserAddToUniverReceiveModel;
import com.example.OTM_web_api.dto.receiveModels.UserSignInReceiveModel;
import com.example.OTM_web_api.dto.receiveModels.UserSignUpReceiveModel;
import com.example.OTM_web_api.entity.Enum.roleEnum.RoleEnum;
import com.example.OTM_web_api.entity.arizaEntity.ArizaEntity;
import com.example.OTM_web_api.entity.univerEntity.UniverEntity;
import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.example.OTM_web_api.repository.ArizaRepository;
import com.example.OTM_web_api.repository.RoleRepository;
import com.example.OTM_web_api.repository.UniverRepository;
import com.example.OTM_web_api.repository.UserRepository;
import com.example.OTM_web_api.service.baseService.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService {


    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;


    private final UniverRepository univerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;
    private final ArizaRepository arizaRepository;

    @Autowired
    public UserService(UniverRepository univerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ObjectMapper objectMapper, ArizaRepository arizaRepository) {
        this.univerRepository = univerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
        this.arizaRepository = arizaRepository;
    }

    public ApiResponse addUser(
            UserSignUpReceiveModel userSignUpReceiveModel
    ){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(userSignUpReceiveModel.getUserName());
        if (optionalUserEntity.isPresent())
            return USER_EXIST;

        Optional<UniverEntity> optionalUniver = univerRepository.findById(userSignUpReceiveModel.getUniverId());
        if(optionalUniver.isEmpty())
            return new ApiResponse("univer not found", false, 409);

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userSignUpReceiveModel.getFirstName());
        userEntity.setUserName(userSignUpReceiveModel.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userSignUpReceiveModel.getPassword()));
        userEntity.setUniverEntity(optionalUniver.get());

        if (userSignUpReceiveModel.getRoleEnum() == null)
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(RoleEnum.STUDENT)));
        else
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(userSignUpReceiveModel.getRoleEnum())));

        userRepository.save(userEntity);

        return SUCCESS;
    }

    public ApiResponse login(
            UserSignInReceiveModel userSignInReceiveModel
    ){
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUserName(userSignInReceiveModel.getUserName());

        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;

        String token = this.generateToken(optionalUserEntity.get());
        SUCCESS.setData(token);

        return SUCCESS;
    }

    private String generateToken(UserEntity userEntity) {
        try {
            return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .setSubject(userEntity.getUsername())
                    .compact();
        }catch (Exception e){
            return null;
        }


    }

    public ApiResponse addUserToUniver(UserAddToUniverReceiveModel userAddToUniverReceiveModel){
        Optional<UserEntity> user1 = userRepository.findByUserName(userAddToUniverReceiveModel.getUserName());
        if (user1.isPresent()){
            UserEntity user=(UserEntity) user1.get();
            if(userRepository.findByPassportNumber(userAddToUniverReceiveModel.getPasswordNumber())){
                return USER_EXIST;
            }
            if (user.getUniverEntity().equals(null)){
                Optional<UniverEntity> univerEntity = univerRepository.findById(userAddToUniverReceiveModel.getUniverId());
                UniverEntity univer=(UniverEntity) univerEntity.get();
                user.setUniverEntity(univer);
                userRepository.save(user);
                return SUCCESS;
            }else{
                return new ApiResponse("this student already in the univer",false,0);
            }
        }
        return new ApiResponse("User doesnt exist",false,0);



    }
    public ApiResponse submitAriza(SubmitArizaReceiveModel submitArizaReceiveModel){
        Optional<UserEntity> userId = userRepository.findById(submitArizaReceiveModel.getUserId());
        if(userId.isPresent()){
            if (userId.get().getUniverEntity().getId()==submitArizaReceiveModel.getUniverId()){
                ArizaEntity arizaEntity = new ArizaEntity();
                arizaEntity.setUniverId(submitArizaReceiveModel.getUniverId());
                arizaEntity.setUserId(submitArizaReceiveModel.getUserId());
                arizaEntity.setText(submitArizaReceiveModel.getText());
                arizaRepository.save(arizaEntity);
            }

        }if(userId.isEmpty()) {
            return USER_EXIST;
        }

    return SUCCESS;

    }


}

package com.example.OTM_web_api.service.arizaService;

import com.example.OTM_web_api.dto.receiveModels.ArizaDTO;
import com.example.OTM_web_api.dto.apiResponse.ApiResponse;
import com.example.OTM_web_api.entity.Enum.univerEnum.ArizaStateEnum;
import com.example.OTM_web_api.entity.arizaEntity.ArizaEntity;
import com.example.OTM_web_api.entity.univerEntity.UniverEntity;
import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.example.OTM_web_api.repository.ArizaRepository;
import com.example.OTM_web_api.repository.UniverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArizaService {
    @Autowired
    ArizaRepository arizaRepository;

    @Autowired
    UniverRepository univerRepository;

    public ApiResponse arizaAdd(ArizaDTO arizaDTO, UserEntity currentUser){
        Optional<UniverEntity> optionalUniver = univerRepository.findById(arizaDTO.getUniverId());
        if(optionalUniver.isEmpty())
            return new ApiResponse("univer not found", false);

        UniverEntity univer = optionalUniver.get();

        final boolean b = currentUser.getUniverEntity().equals(univer);
//      System.out.println(b);

        if(b){
            ArizaEntity ariza = new ArizaEntity();
            ariza.setUniverId(univer.getId());
            ariza.setText(arizaDTO.getText());
            ariza.setStatus(ArizaStateEnum.IN_PROCESS);
            ariza.setUserId(currentUser.getId());
            arizaRepository.save(ariza);
            return new ApiResponse("your appl accepted like new !", true);
        }else {
            return new ApiResponse("you can not appl to this univer!", false);
        }
    }

    public ApiResponse changeStatus(Long arizaId, ArizaStateEnum status) {
        Optional<ArizaEntity> optionalAriza = arizaRepository.findById(arizaId);
        if(optionalAriza.isEmpty())
            return new ApiResponse("ariza not found", false);

        ArizaEntity arizaEntity = optionalAriza.get();
        arizaEntity.setStatus(status);
        arizaRepository.save(arizaEntity);
        return new ApiResponse("ariza status have changed " + status, true );
    }
}

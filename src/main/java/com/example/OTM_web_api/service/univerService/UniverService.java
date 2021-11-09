package com.example.OTM_web_api.service.univerService;

import com.example.OTM_web_api.dto.apiResponse.ApiResponse;
import com.example.OTM_web_api.entity.univerEntity.UniverEntity;
import com.example.OTM_web_api.repository.UniverRepository;
import com.example.OTM_web_api.service.baseService.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniverService implements BaseService {

    private final UniverRepository univerRepository;

    public UniverService(UniverRepository univerRepository) {
        this.univerRepository = univerRepository;
    }

    public ApiResponse addUniver(String univerName){

        Optional<UniverEntity> optionalUniverEntity = univerRepository.findByUniverName(univerName);
        if(optionalUniverEntity.isEmpty()){

            UniverEntity univerEntity = new UniverEntity();
            univerEntity.setUniverName(univerName);
            univerRepository.save(univerEntity);
            return SUCCESS;

        } if (optionalUniverEntity.isPresent()){
            return UNIVER_EXIST;
        }

        return SUCCESS;
    }
}

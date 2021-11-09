package com.example.OTM_web_api;

import com.example.OTM_web_api.entity.Enum.roleEnum.RoleEnum;
import com.example.OTM_web_api.entity.roleEntity.RoleEntity;
import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.example.OTM_web_api.repository.RoleRepository;
import com.example.OTM_web_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String first;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(first.equals("always")) {
            RoleEntity student=new RoleEntity(RoleEnum.STUDENT);
            roleRepository.save(student);

            RoleEntity admin =new RoleEntity();
            admin.setRoleEnum(RoleEnum.ADMIN_OTM);
            roleRepository.save(admin);

            UserEntity root=new UserEntity();
            root.setUserName("root");
            root.setPassword(passwordEncoder.encode("root"));
            root.setRoleEntityList(List.of(admin));
            userRepository.save(root);
        }
    }
}

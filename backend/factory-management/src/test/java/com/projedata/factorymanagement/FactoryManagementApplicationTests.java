package com.projedata.factorymanagement;

import com.projedata.factorymanagement.config.ContainersEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FactoryManagementApplicationTests extends ContainersEnvironment {

    @Test
    void main() {
        FactoryManagementApplication.main(new String[]{});
    }

}

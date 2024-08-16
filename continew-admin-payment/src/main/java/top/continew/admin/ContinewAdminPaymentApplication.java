package top.continew.admin;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import top.continew.starter.extension.crud.annotation.EnableCrudRestController;
import top.continew.starter.web.annotation.EnableGlobalExceptionHandler;

@Slf4j
@RestController
@EnableFileStorage
@SpringBootApplication
@RequiredArgsConstructor
@EnableCrudRestController
@EnableGlobalExceptionHandler
@EnableMethodCache(basePackages = "top.continew.admin")
public class ContinewAdminPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContinewAdminPaymentApplication.class, args);
    }

}

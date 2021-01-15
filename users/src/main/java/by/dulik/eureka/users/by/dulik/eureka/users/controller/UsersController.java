package by.dulik.eureka.users.by.dulik.eureka.users.controller;

import by.dulik.eureka.users.by.dulik.eureka.users.model.CreateUserRequestModel;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RequestMapping("v1/users")
@RestController
public class UsersController {

    private final Environment environment;

    public UsersController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo() {
        return ResponseEntity.ok(Collections.singletonMap("ok", true));
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        return ResponseEntity.ok(Collections.singletonMap("port", environment.getProperty("local.server.port")));
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel createUser) {
        return "Create user method is called";
    }


}

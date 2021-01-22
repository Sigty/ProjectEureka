package by.dulik.eureka.info.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequestMapping("v1/info")
@RestController
public class InfoController {

    private final Environment environment;

    public InfoController(Environment environment) {
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
}

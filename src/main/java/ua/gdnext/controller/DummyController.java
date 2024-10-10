package ua.gdnext.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class DummyController {

    @GetMapping("/required-params")
    public Map<String, String> getRequiredParams() {
        return Map.of();
    }

    @PostMapping("/required-params")
    public Map<String, String> updateRequiredParams(@RequestBody Map<String, String> params) {
        return Map.of();
    }
}

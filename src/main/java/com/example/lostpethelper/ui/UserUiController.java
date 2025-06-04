package com.example.lostpethelper.ui;

import com.example.lostpethelper.dto.ui.UserRs;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ui/users")
public class UserUiController {

    private final RestTemplate restTemplate;

    public UserUiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getUsers(Model model) {
        ResponseEntity<UserRs[]> response = restTemplate.getForEntity("http://localhost:8080/api/v1/users", UserRs[].class);
        model.addAttribute("users", List.of(response.getBody()));
        return "users";
    }

    @PostMapping("/{id}/remove")
    public String removeUser(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        restTemplate.exchange("http://localhost:8080/api/v1/users/" + id + "/remove", HttpMethod.PATCH, entity, Void.class);
        return "redirect:/ui/users";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestParam String name,
                             @RequestParam String lastname,
                             @RequestParam String phoneNumber,
                             @RequestParam String email) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("lastname", lastname);
        body.put("phoneNumber", phoneNumber);
        body.put("email", email);

        restTemplate.postForEntity("http://localhost:8080/api/v1/users", body, Void.class);
        return "redirect:/ui/users";
    }
}

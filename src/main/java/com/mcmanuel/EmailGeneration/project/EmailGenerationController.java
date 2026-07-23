package com.mcmanuel.EmailGeneration.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailGenerationController {
    private final EmailGenerationService service;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmailResponse(@RequestBody EmailRequest emailRequest){
        return new ResponseEntity<>(service.generate(emailRequest), HttpStatus.OK);
    }

}

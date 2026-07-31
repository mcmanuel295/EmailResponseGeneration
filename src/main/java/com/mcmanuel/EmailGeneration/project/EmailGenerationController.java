package com.mcmanuel.EmailGeneration.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
@CrossOrigin("*")
@Slf4j
public class EmailGenerationController {
    private final EmailGenerationService service;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmailResponse(@RequestBody EmailRequest emailRequest){
        log.info("incoming accessing request");
        return new ResponseEntity<>(service.generate(emailRequest), HttpStatus.OK);
    }

}

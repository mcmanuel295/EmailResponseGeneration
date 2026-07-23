package com.mcmanuel.EmailGeneration.project;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailGenerationService {
    private final WebClient webClient= WebClient.builder().build();
    @Value("${gemini.api.key}")
    private String gemini_key;
    @Value("${gemini.api.key")
    private String gemini_url;


    public String generate(EmailRequest emailRequest) {

        String prompt = buildPrompt(emailRequest);

        Map<String,String> responseBody = Map.of("input", prompt);

        String tree = webClient.post()
                .uri(gemini_url+gemini_key)
                .header("Content-Type","application/json")
                .bodyValue(responseBody.get("input"))
                .retrieve().bodyToMono(String.class).block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(tree);
        return root.path("steps").get(1)
                .path("content").get(0)
                .path("text").toString();
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder builder = new StringBuilder();
        builder.append("Generate a professional email from the followiwng email content. Please don't add a subject line")
                .append("original email : ").append(emailRequest.getContent());


        if (emailRequest.getTone()!= null && !emailRequest.getTone().isEmpty()) {
            builder.append("use a ").append(emailRequest.getTone()).append(" tone");
        }

        return builder.toString();
    }
}

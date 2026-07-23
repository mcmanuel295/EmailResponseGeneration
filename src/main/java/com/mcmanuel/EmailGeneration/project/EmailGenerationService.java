package com.mcmanuel.EmailGeneration.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailGenerationService {
    private final WebClient webClient= WebClient.builder().build();
    @Value("${gemini.api.key}")
    private String geminikey;
    @Value("${gemini.api.url}")
    private String geminiUurl;


    public String generate(EmailRequest emailRequest) {

        String prompt = buildPrompt(emailRequest);

        Map<String,String> responseBody = Map.of(
                "input", prompt,
                "model","gemini-3.6-flash"
        );

        String response = webClient.post()
                .uri(geminiUurl)
                .header("Content-Type","application/json")
                .header("x-goog-api-key",geminikey)
                .bodyValue(responseBody)
                .retrieve().bodyToMono(String.class).block();

        return extractResponseContent(response);
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

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("steps").get(1)
                    .path("content").get(0)
                    .path("text").toString();

        }
        catch (Exception ex) {
            log.error("{}", ex.getMessage());
            return "Error processing request: "+ex.getMessage();
        }
    }
}

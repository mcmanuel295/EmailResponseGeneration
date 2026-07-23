package com.mcmanuel.EmailGeneration.project;

import lombok.Data;

@Data
public class EmailRequest {
    private String content;
    private String tone;
}

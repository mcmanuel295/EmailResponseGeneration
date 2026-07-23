package com.mcmanuel.EmailGeneration.project;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EmailRequest {
    private String content;
    private String tone;
}

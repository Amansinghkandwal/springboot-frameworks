package com.example.spring_boot.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime localDateTime;

    private int status;

    private String error;

    private String errorMessage;

}

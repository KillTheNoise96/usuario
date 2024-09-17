package com.api.usuario.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String code;
    private String message;
}

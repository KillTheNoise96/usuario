package com.api.usuario.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {
    private Integer id;
    private String number;
    private String cityCode;
    private String countryCode;
}

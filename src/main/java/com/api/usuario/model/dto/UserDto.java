package com.api.usuario.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean active;
}

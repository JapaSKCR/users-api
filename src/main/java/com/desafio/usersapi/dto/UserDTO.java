package com.desafio.usersapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Nome")
    @Size(min = 2, max = 100, message = "Nome")
    private String name;

    @NotBlank(message = "E-mail")
    private String email;

    @Size(min = 1)
    private String sex;

    @NotNull
    private String birthDate;

    @NotBlank
    @CPF
    private String cpf;

    private String placeOfBirth;

    private String nationality;


}


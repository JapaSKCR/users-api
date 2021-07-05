package com.desafio.usersapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class User {

    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @Column(name = "CREATED", nullable = false)
    private Timestamp createdDate;

    @Column(name = "LAST_UPDATE", nullable = true)
    private Timestamp lastUpdate;

    @Column(name = "NATIONALITY")
    private String nationality;



}
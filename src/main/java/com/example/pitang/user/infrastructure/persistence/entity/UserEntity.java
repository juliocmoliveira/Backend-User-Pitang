package com.example.pitang.user.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class UserEntity {

    public UserEntity(String firstName, String lastName, String email, LocalDate birthday, String login, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "primeiro_nome")
    private String firstName;

    @NotNull
    @Column(name = "ultimo_nome")
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate birthday;

    @NotNull
    @Column(name = "login", unique = true)
    private String login;

    @NotNull
    @Column(name = "senha")
    private String password;

    @NotNull
    @Column(name = "telefone")
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;
}

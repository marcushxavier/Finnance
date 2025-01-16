package br.com.finnance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;

    public User(){}
    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }
}


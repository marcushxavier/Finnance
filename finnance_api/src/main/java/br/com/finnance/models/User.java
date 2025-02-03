package br.com.finnance.models;

import br.com.finnance.utils.ClassToSting;
import br.com.finnance.utils.GetObjectFieldsName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.UUID;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    public User(){}

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return ClassToSting.turnToString(this);
    }
}


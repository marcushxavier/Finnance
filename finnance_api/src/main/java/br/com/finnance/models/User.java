package br.com.finnance.models;

import br.com.finnance.utils.ClassToSting;
import br.com.finnance.utils.UpdateClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    public User(){}

    public User(User userData){
        this.id = UUID.randomUUID();

        String[] blackList = {"id"};
        new UpdateClass<User>().update(this, userData, blackList);
    }

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


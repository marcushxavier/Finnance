package br.com.finnance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Entity(name = "note")
@Table(name = "notes")
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID ownerId;
    private String title;
    private BigDecimal value;
    private boolean isOutflow;
    private String category;
    private Date date;

    public Note() {
    }

    public Note(UUID ownerId, String title, BigDecimal value, boolean isOutflow, String category, Date date) {
        this.ownerId = ownerId;
        this.title = title;
        this.value = value;
        this.isOutflow = isOutflow;
        this.category = category;
        this.date = date;
    }

    public Stream<String> updateNote() {
        Stream<String[]> objFields = Arrays.stream(Note.class.getDeclaredFields()).map(Field::toString).map(e -> {
            return e.split("\\.");
        });

        return objFields.map(e -> e[e.length -1]);
    }

}
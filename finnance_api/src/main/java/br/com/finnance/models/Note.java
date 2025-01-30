package br.com.finnance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity(name = "note")
@Table(name = "notes")
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "owner_id")
    private UUID ownerId;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "value")
    private BigDecimal value;

    @Column(nullable = false, name = "flow", length = 1)
    private short flow; //-1 or 1

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "date")
    private Date date;

    public Note() {
    }

    public Note(UUID ownerId, String title, BigDecimal value, short flow, String category, Date date) {
        this.ownerId = ownerId;
        this.title = title;
        this.value = value;
        this.flow = flow;
        this.category = category;
        this.date = date;
    }

}
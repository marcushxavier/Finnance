package br.com.finnance.models;

import br.com.finnance.utils.ClassToSting;
import br.com.finnance.utils.UpdateClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
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
    private int flow; //-1 or 1

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "date")
    private LocalDate date;

    public Note() {
    }

    public Note(Note noteData) {
        String[] blackList = {"id"};
        new UpdateClass<Note>().update(this, noteData, blackList);
    }

    @Override
    public String toString() {
        return ClassToSting.turnToString(this);
    }
}
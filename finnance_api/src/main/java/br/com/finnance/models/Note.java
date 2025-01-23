package br.com.finnance.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity(name = "note")
@Table(name = "notes")
@Getter
@Setter
public class Note {
    @Id
    private int id;
    private UUID ownerId;
    private String title;
    private BigDecimal value;
    @Value("is_outflow")
    private boolean isOutflow;
    private String category;
    private Date date;

    public Note() {}

    public Note(UUID ownerId, String title, BigDecimal value, boolean isOutflow, String category, Date date) {
        this.ownerId = ownerId;
        this.title = title;
        this.value = value;
        this.isOutflow = isOutflow;
        this.category = category;
        this.date = date;
    }
}

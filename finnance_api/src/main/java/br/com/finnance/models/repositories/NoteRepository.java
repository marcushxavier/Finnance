package br.com.finnance.models.repositories;

import br.com.finnance.models.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Note note WHERE note.ownerId = :ownerId")
    void deleteAllByOwnerId(@Param("ownerId") UUID ownerId);

    List<Note> findByOwnerId(UUID ownerId);
}

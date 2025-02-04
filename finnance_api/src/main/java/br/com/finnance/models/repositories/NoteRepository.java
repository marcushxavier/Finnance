package br.com.finnance.models.repositories;

import br.com.finnance.models.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Note n where n.owner_id=?1")
    public void deleteAllByOwnerId(UUID ownerId);
}

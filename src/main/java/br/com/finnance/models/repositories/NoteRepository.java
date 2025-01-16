package br.com.finnance.models.repositories;

import br.com.finnance.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {}

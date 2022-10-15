package com.devmountain.noteApp.repositories;

import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devmountain.noteApp.entities.Notes;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Notes, Long> {

    List<Notes> findAllByUserEquals(User user);
}

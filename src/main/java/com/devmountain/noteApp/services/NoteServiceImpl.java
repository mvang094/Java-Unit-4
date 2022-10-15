package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;
import com.devmountain.noteApp.entities.Notes;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.NoteRepo;
import com.devmountain.noteApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private NoteRepo noteRepo;

    @Override
    public void addNote(NoteDto noteDto, Long userId)
    {
        Optional<User> userOptional = userRepo.findById(userId);
        Notes note = new Notes(noteDto);
        userOptional.ifPresent(note::setUser);
        noteRepo.saveAndFlush(note);
    }

    @Override
    @Transactional
    public void deleteNoteById(Long noteId){
        Optional<Notes> notesOptional = noteRepo.findById(noteId);
        notesOptional.ifPresent(note -> noteRepo.delete(note));
    }

    @Override
    @Transactional
    public void updateNoteById(NoteDto noteDto){
        Optional<Notes> noteOptional = noteRepo.findById(noteDto.getId());
        noteOptional.ifPresent(notes -> {
            notes.setBody(noteDto.getBody());
            noteRepo.saveAndFlush(notes);
        });
    }

    @Override
    public List<NoteDto> getAllNotes(Long userId)
    {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent())
        {
            List<Notes> noteList = noteRepo.findAllByUserEquals(userOptional.get());
            return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<NoteDto> getNotebyId(Long noteId){
        Optional<Notes> noteOptional = noteRepo.findById(noteId);
        if(noteOptional.isPresent())
            return Optional.of(new NoteDto(noteOptional.get()));
        return Optional.empty();
    }
}

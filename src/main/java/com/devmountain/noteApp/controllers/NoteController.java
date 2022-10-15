package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.NoteDto;
import com.devmountain.noteApp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/user/{userId}")
    public void newNotes (@RequestBody NoteDto noteDto, @PathVariable Long userId){
        noteService.addNote(noteDto, userId);
    }

    @GetMapping("/user/{userId}")
    public List<NoteDto> getNotesByUser(@PathVariable Long userId){
        return noteService.getAllNotes(userId);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNotes(@PathVariable Long noteId) {
        noteService.deleteNoteById(noteId);
    }

    @PutMapping("/{noteId}")
    public void updateNotes(@RequestBody NoteDto noteDto, @PathVariable Long noteId)
    {
        noteService.updateNoteById(noteDto);
    }

    @GetMapping("/{noteId}")
    public Optional<NoteDto> getNotesByNote(@PathVariable Long noteId)
    {
        return noteService.getNotebyId(noteId);
    }

}

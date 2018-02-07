package com.example.easynotes.controller;

import java.util.*;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NotesRepository noteRepository;
    // get all notes
    @CrossOrigin(origins = "*" )
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    // create a new note
    @CrossOrigin(origins = "*" )
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }
    //get a single note
    @CrossOrigin(origins = "*" )
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId){
        Note note = noteRepository.findOne(noteId);
        if(note == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

    //update a note
    @CrossOrigin(origins = "*" )
    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails){
        Note note = noteRepository.findOne(noteId);

        if(note == null ){
            return ResponseEntity.notFound().build();
        }

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

    //delete a note
    @CrossOrigin(origins = "*" )
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}

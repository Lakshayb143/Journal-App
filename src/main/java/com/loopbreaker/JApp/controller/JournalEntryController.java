package com.loopbreaker.JApp.controller;


import com.loopbreaker.JApp.entity.JournalEntry;
import com.loopbreaker.JApp.servies.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
{


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllEntries")
    public ResponseEntity<List<JournalEntry>> getAll()
    {
        List<JournalEntry> all_entries = journalEntryService.getAll();
        if (all_entries != null && !all_entries.isEmpty()){
            return new ResponseEntity<>(all_entries, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/createEntry")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry)
    {

        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(String.format("Entry added successfully \n %s", journalEntry), HttpStatus.CREATED);

        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }


    @GetMapping("getById/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){

        Optional<JournalEntry> entryById = journalEntryService.getEntryById(myId);
        if (entryById.isPresent()){
            return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("getById/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId)
    {

        JournalEntry jEntry = journalEntryService.getEntryById(myId).orElse( null);
        if (jEntry == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else{
            journalEntryService.deleteByid(myId);
            return new ResponseEntity<>(String.format("Entry deleted successfully \n\t\t %s",jEntry),HttpStatus.NO_CONTENT);
        }



    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id ,@RequestBody JournalEntry newEntry )
    {
        JournalEntry jEntry = journalEntryService.getEntryById(id).orElse( null);
        if( jEntry != null)
        {
            jEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle():jEntry.getTitle());
            jEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): jEntry.getContent());
            journalEntryService.saveEntry(jEntry);
            return new ResponseEntity<>(String.format("Entry updated successfully to \n %s",jEntry), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
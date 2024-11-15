package com.loopbreaker.JApp.controller;


import com.loopbreaker.JApp.entity.JournalEntry;
import com.loopbreaker.JApp.entity.User;
import com.loopbreaker.JApp.servies.JournalEntryService;
import com.loopbreaker.JApp.servies.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
{


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("all/{userName}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String userName)
    {

        User user = userService.getByUsername(userName);
//        List<JournalEntry> all_entries = journalEntryService.getAll();
        List<JournalEntry> all_entries = user.getJournalEntries();
        if (all_entries != null && !all_entries.isEmpty()){
            return new ResponseEntity<>(all_entries, HttpStatus.OK);
        }

        return new ResponseEntity<>("No entry found",HttpStatus.NOT_FOUND);

    }

    @PostMapping("/createEntry/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName)
    {
        try {
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>(String.format("Entry added successfully \n %s", journalEntry), HttpStatus.CREATED);

        } catch (Exception e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
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

    @DeleteMapping("delete/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName)
    {
            journalEntryService.deleteByid(myId, userName);
            return new ResponseEntity<>(String.format("Entry deleted successfully \n\t\t "),HttpStatus.NO_CONTENT);


    }

    @PutMapping("updateById/{userName}/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id ,
                                               @RequestBody JournalEntry newEntry,
                                               @PathVariable String userName)
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
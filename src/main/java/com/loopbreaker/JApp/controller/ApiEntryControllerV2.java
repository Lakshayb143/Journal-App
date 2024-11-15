package com.loopbreaker.JApp.controller;


import com.loopbreaker.JApp.entity.JournalEntry;
import com.loopbreaker.JApp.servies.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class ApiEntryControllerV2
{


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllEntries")
    public List<JournalEntry> getAll()
    {
        return  journalEntryService.getAll();

    }

    @PostMapping("/createEntry")
    public String createEntry(@RequestBody JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);

        return "Entry added successfully";


    }


    @GetMapping("getById/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId){
        return null;
    }

    @DeleteMapping("getById/{myId}")
    public JournalEntry deleteEntryById(@PathVariable ObjectId myId)
    {
        return null;
    }

    @PutMapping("updateById/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id ,@RequestBody JournalEntry myEntry )
    {
        return null;
    }

}
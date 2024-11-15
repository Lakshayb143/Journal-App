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
public class JournalEntryController
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



        return String.format("Entry added successfully \n %s", journalEntry);


    }


    @GetMapping("getById/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId){

        return journalEntryService.getEntryById(myId).orElse( null);

    }

    @DeleteMapping("getById/{myId}")
    public String deleteEntryById(@PathVariable ObjectId myId)
    {

        JournalEntry jEntry = journalEntryService.getEntryById(myId).orElse( null);
        if (jEntry == null)
        {
            return "No Entry found";

        }else{
            journalEntryService.deleteByid(myId);
            return String.format("Entry deleted successfully \n\t\t %s",jEntry);
        }



    }

    @PutMapping("updateById/{id}")
    public String updateJournalById(@PathVariable ObjectId id ,@RequestBody JournalEntry newEntry )
    {
        JournalEntry jEntry = journalEntryService.getEntryById(id).orElse( null);
        if( jEntry != null)
        {
            jEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle():jEntry.getTitle());
            jEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): jEntry.getContent());
            journalEntryService.saveEntry(jEntry);
            return String.format("Entry updated successfully to \n %s",jEntry);
        }else {
            return "No entry found !!";
        }


    }

}
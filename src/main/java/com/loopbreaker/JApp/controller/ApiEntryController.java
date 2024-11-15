//package com.loopbreaker.Starting_API.controller;
//
//
//import com.loopbreaker.Starting_API.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class ApiEntryController
//{
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping("/getAllEntries")
//    public List<JournalEntry> getAll()
//    {
//        return new ArrayList<>(journalEntries.values());
//
//    }
//
//    @PostMapping("/createEntry")
//    public String createEntry(@RequestBody JournalEntry journalEntry)
//    {
//        journalEntries.put(journalEntry.getId(), journalEntry);
//        return "Entry added successfully";
//
//
//    }
//
//
//    @GetMapping("getById/{myId}")
//    public JournalEntry getEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//
//    }
//
//    @DeleteMapping("getById/{myId}")
//    public JournalEntry deleteEntryById(@PathVariable Long myId)
//    {
//        return journalEntries.remove(myId);
//
//    }
//
//    @PutMapping("updateById/{id}")
//    public JournalEntry updateJournalById(@PathVariable Long id ,@RequestBody JournalEntry myEntry )
//    {
//        return journalEntries.put(id,myEntry);
//    }
//
//}
package com.loopbreaker.JApp.servies;

import com.loopbreaker.JApp.entity.JournalEntry;
import com.loopbreaker.JApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService
{

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }


}

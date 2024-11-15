package com.loopbreaker.JApp.servies;

import com.loopbreaker.JApp.entity.JournalEntry;
import com.loopbreaker.JApp.entity.User;
import com.loopbreaker.JApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService
{

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.getByUsername(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry newEntry = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(newEntry);
        userService.saveEntry(user);
    }


    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteByid(ObjectId id, String userName){

        User user = userService.getByUsername(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }


}

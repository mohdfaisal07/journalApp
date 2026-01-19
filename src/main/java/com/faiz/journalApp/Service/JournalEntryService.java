package com.faiz.journalApp.Service;

import com.faiz.journalApp.Entity.JournalEntry;
import com.faiz.journalApp.Entity.User;
import com.faiz.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
@Autowired
    private JournalEntryRepository journalEntryRepository ;
@Autowired
private UserService userService;

@Transactional
public void saveEntry(JournalEntry journalEntry, String username){
    try {
        User user = userService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }catch(Exception e){
        System.out.println(e);
     throw new RuntimeException("Fatt gaya code",e);
    }

}

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

public List<JournalEntry>getAll(){
   return journalEntryRepository.findAll();
}

public Optional<JournalEntry> findById(ObjectId id){
    return journalEntryRepository.findById(id);
}

@Transactional
public Boolean deleteById(ObjectId id, String username){
    boolean removed=false;
    try {
        User user = userService.findByUsername(username);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed){
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("some problem occurred while deleting by id",e);
    }
    return removed;
}

}

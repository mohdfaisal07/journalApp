package com.faiz.journalApp.Controllers;


import com.faiz.journalApp.Entity.JournalEntry;
import com.faiz.journalApp.Entity.User;
import com.faiz.journalApp.Service.JournalEntryService;
import com.faiz.journalApp.Service.UserService;
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
public class JournalEntryControllersV2 {
   @Autowired
   private JournalEntryService journalEntryService;

@Autowired
private UserService userService;

    @GetMapping("{username}")
  public  ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


@PostMapping("{username}")
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username){
        journalEntry.setDate(LocalDateTime.now());
        try {
        journalEntryService.saveEntry(journalEntry, username);
 return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
  }

  @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId){
      Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
if (journalEntry.isPresent()){
    return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
}
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

    @DeleteMapping ("id/{username}/{myId}")
    public ResponseEntity<?>  deleteJournalEntryByID(@PathVariable String username,@PathVariable ObjectId myId){
       journalEntryService.deleteById(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry, @PathVariable String username){
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setJournal(newEntry.getJournal() != null && !newEntry.getJournal().isEmpty() ? newEntry.getJournal() : old.getJournal());
        journalEntryService.saveEntry(old);
        return  new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

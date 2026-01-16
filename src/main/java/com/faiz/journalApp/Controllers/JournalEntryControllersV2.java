package com.faiz.journalApp.Controllers;


import com.faiz.journalApp.Entity.JournalEntry;
import com.faiz.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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


    @GetMapping()
  public  ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalEntryService.getAll();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


@PostMapping()
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        try {
        journalEntryService.saveEntry(myEntry);
 return new ResponseEntity<>(myEntry, HttpStatus.OK);
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

    @DeleteMapping ("id/{myId}")
    public ResponseEntity<?>  deleteJournalEntryByID(@PathVariable ObjectId myId){
       journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id , @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
            old.setJournal(newEntry.getJournal() != null && !newEntry.getJournal().equals("")? newEntry.getJournal() : old.getJournal());
        journalEntryService.saveEntry(old);
        return  new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

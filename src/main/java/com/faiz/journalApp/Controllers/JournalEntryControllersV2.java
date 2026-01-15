package com.faiz.journalApp.Controllers;


import com.faiz.journalApp.Entity.JournalEntry;
import com.faiz.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllersV2 {
   @Autowired
   private JournalEntryService journalEntryService;


    @GetMapping()
  public  List <JournalEntry> getAll(){
        return journalEntryService.getAll();
  }


@PostMapping()
  public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
 return myEntry;
  }

  @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryByID(@PathVariable ObjectId myId){
      return journalEntryService.findById(myId).orElse(null);
  }

    @DeleteMapping ("id/{myId}")
    public boolean  deleteJournalEntryByID(@PathVariable ObjectId myId){
       journalEntryService.deleteById(myId);
       return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id , @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
            old.setJournal(newEntry.getJournal() != null && !newEntry.getJournal().equals("")? newEntry.getJournal() : old.getJournal());

        }
        journalEntryService.saveEntry(old);
        return old;
    }
}

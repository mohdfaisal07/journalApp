package com.faiz.journalApp.Cache;

import com.faiz.journalApp.Entity.ConfigJournalAppEntity;
import com.faiz.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String, String> APPCache = new HashMap<>();

    @Autowired
    public ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            APPCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
            APPCache.put(configJournalAppEntity.getKey2(), configJournalAppEntity.getValue2());
        }
        System.out.println("Cache initialized: " + APPCache);

    }

    public String getValue(String key) {
        return APPCache.get(key);
    }
}



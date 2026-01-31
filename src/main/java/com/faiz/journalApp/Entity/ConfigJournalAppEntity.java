package com.faiz.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {
    @Id
    private ObjectId id;

    private String Key;
    private String Value;
    private String Key2;
    private String Value2;
}

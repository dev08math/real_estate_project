package com.backendservice.models;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Suggestion")
@Getter
public class Suggestion implements Comparable<Suggestion>{
    private final String word;
    private Integer freq;

    public Suggestion(String word) {
        this.word = word;
        freq = 1;
    }
    public void incrementFreq() {
        this.freq++;
    }
    @Override
    public int compareTo(Suggestion suggestion) {
        return suggestion.getFreq() - this.freq;
    }
}

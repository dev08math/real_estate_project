package com.backendservice.models;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "TrieNode")
public class TrieNode {
    @Id
    private String id;
    @Indexed()
    @Getter private final String prefix;
    private final List<Suggestion> suggestions;
    private static final Integer MAXSIZE = 10;
    public TrieNode(String prefix){
        this.prefix = prefix;
        suggestions = new ArrayList<>();
    }
    public int getSuggestionsCount() {
        return suggestions.size();
    }
    public List<String> getSuggestions(){
        Collections.sort(suggestions);
        List<String> res = new ArrayList<>();
        for(Suggestion s: suggestions){
            res.add(s.getWord());
        }
        return res;
    }
    public void addWord(String word) {
        boolean found = false;
        for(Suggestion suggestion:suggestions) {
            if(suggestion.getWord().equals(word)) {
                suggestion.incrementFreq();
                found = true;
                break;
            }
        }
        if(!found)
            suggestions.add(new Suggestion(word));

        Collections.sort(suggestions);
        if(suggestions.size() > MAXSIZE)
            suggestions.remove(suggestions.size()-1);
    }
}

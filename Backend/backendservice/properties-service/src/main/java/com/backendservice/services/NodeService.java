package com.backendservice.services;

import com.backendservice.models.TrieNode;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NodeService {
    @Autowired
    private MongoTemplate mt;
    private final String COLLECTION ="TrieNode";
    private TrieNode findByPrefix(String prefix) {
        Query query = new Query().addCriteria(Criteria.where("prefix").is(prefix));
        return mt.findOne(query, TrieNode.class, COLLECTION);
    }

    private boolean ifPrefixExists(String prefix){
        Query query = new Query().addCriteria(Criteria.where("prefix").exists(true));
        return mt.exists(query, TrieNode.class);
    }
    public List<String> suggest(String word){
        word = word.toLowerCase();
        return findByPrefix(word).getSuggestions();
    }

    public void insert(String word) {
        word = word.toLowerCase();
        for (int i = 1; i <= word.length(); i++) {
            String tempPrefix = word.substring(0, i);
            if(!ifPrefixExists(word)) mt.save(new TrieNode(tempPrefix));
            Query query = new Query().addCriteria(new Criteria().andOperator(
                    Criteria.where("prefix").is(tempPrefix),
                    Criteria.where("suggestions.word").is(word)
            ));
            if(mt.exists(query, TrieNode.class, COLLECTION)){
                BasicDBList updates = new BasicDBList();
                BasicDBObject jsonSug = new BasicDBObject("word",word);
                jsonSug.append("freq", 1);
                BasicDBObject sugg = new BasicDBObject("suggestions", jsonSug);
                BasicDBObject u = new BasicDBObject("$addToSet", sugg);
                BasicDBObject q = new BasicDBObject("prefix", tempPrefix);
                BasicDBObject oneQuery = new BasicDBObject();
                oneQuery.append("q", q);
                oneQuery.append("u", u);
                updates.add(oneQuery);
                BasicDBObject command = new BasicDBObject("update","TrieNode");
                command.append("updates", updates);
                mt.executeCommand(String.valueOf(command));
            }
            else{
                BasicDBList updates = new BasicDBList();
                BasicDBObject u = new BasicDBObject("$inc", new BasicDBObject("suggestions.$.freq",1));
                BasicDBObject q  = new BasicDBObject();
                q.append("prefix", tempPrefix);
                q.append("suggestions.word", word);
                BasicDBObject oneQuery = new BasicDBObject();
                oneQuery.append("q", q);
                oneQuery.append("u", u);
                updates.add(oneQuery);
                BasicDBObject command = new BasicDBObject("update", COLLECTION);
                command.append("updates", updates);
                mt.executeCommand(String.valueOf(command));
            }

            BasicDBList updates = new BasicDBList();
            BasicDBObject q  = new BasicDBObject("prefix",tempPrefix);
            BasicDBObject oneQuery = new BasicDBObject();
            oneQuery.append("q", q);
            BasicDBObject sortSuggestions = new BasicDBObject("$each", new BasicDBList());
            sortSuggestions.append("$sort", new BasicDBObject("freq",-1));
            BasicDBObject u = new BasicDBObject("$push", new BasicDBObject("suggestions",sortSuggestions));
            oneQuery.append("u", u);
            updates.add(oneQuery);
            BasicDBObject command = new BasicDBObject("update", COLLECTION);
            command.append("updates", updates);
            mt.executeCommand(String.valueOf(command));

            TrieNode node = findByPrefix(tempPrefix);
            int cnt = node.getSuggestionsCount();

            int MAXCOUNT = 10;
            if(cnt > MAXCOUNT) {
                BasicDBList updatesC = new BasicDBList();
                BasicDBObject qC  = new BasicDBObject("prefix",tempPrefix);
                BasicDBObject uC = new BasicDBObject("$pop", new BasicDBObject("suggestions",1));
                BasicDBObject oneQueryC = new BasicDBObject();
                oneQueryC.append("q", qC);
                oneQueryC.append("u", uC);
                updatesC.add(oneQueryC);
                BasicDBObject commandC = new BasicDBObject("update", COLLECTION);
                commandC.append("updates", updatesC);
                mt.executeCommand(String.valueOf(command));
            }
        }
    }
}

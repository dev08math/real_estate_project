package com.backend.realestatebackend.security.details;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.realestatebackend.collections.UserCollection;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MongoTemplate mt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(username));
        List<UserCollection> users = mt.find(query, UserCollection.class);
        if(users.size() == 0) throw  new UsernameNotFoundException("User Not Found with username: " + username);
        UserCollection user = users.get(0);

        return CustomUserDetailsImpl.build(user);
    }
    
}

package com.stackroute.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;

    public int sequenceNumber(String sequenceName){
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("sequenceNumber",1);
        DBsequence dBsequence = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),DBsequence.class);
        return !Objects.isNull(dBsequence)?dBsequence.getSequenceNumber():1;
    }
}

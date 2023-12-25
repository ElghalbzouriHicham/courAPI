package com.example.APIcour.Repository;

import com.example.APIcour.Model.Cour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourRepository extends MongoRepository<Cour,String> {

}

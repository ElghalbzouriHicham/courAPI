package com.example.APIcour.Repository;

import com.example.APIcour.Model.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationRepository extends MongoRepository<Evaluation,String> {
}

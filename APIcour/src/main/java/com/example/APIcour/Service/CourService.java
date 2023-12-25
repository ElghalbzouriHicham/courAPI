package com.example.APIcour.Service;

import com.example.APIcour.Model.Cour;
import com.example.APIcour.Model.Evaluation;
import com.example.APIcour.Repository.CourRepository;
import com.example.APIcour.Repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourService {
    @Autowired
    CourRepository courRepository;
    @Autowired
    EvaluationRepository evaluationRepository;

    public ResponseEntity<?> save(Cour cour){
        if(cour==null){
            return new ResponseEntity<>("null cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getTypeCour()==null){
            return new ResponseEntity<>("null type cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getCategorieCour()==null){
            return new ResponseEntity<>("null category cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getDescriptionCour()==null){
            return new ResponseEntity<>("null description cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getNomCour()==null){
            return new ResponseEntity<>("null nom cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getPrixCour()==null){
            return new ResponseEntity<>("null prix cour",HttpStatus.BAD_REQUEST);
        }
        if(cour.getEvaluation()==null){
            return new ResponseEntity<>("null Evaluation cour",HttpStatus.BAD_REQUEST);
        }
        try{
            Evaluation evaluation= cour.getEvaluation();
            evaluationRepository.save(evaluation);
            cour.setListIdEtudiants(new ArrayList<>());
            this.courRepository.save(cour);
            return new ResponseEntity<>("cour added successfuly",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> Delete(String id  ){
        Optional<Cour> optionalCour=this.courRepository.findById(id);
        if(optionalCour.isEmpty()){
            return new ResponseEntity<>("null cour",HttpStatus.BAD_REQUEST);
        }
        try {
            Cour cour = optionalCour.get();
            Evaluation evaluation = cour.getEvaluation();
            if (evaluation != null) {
                evaluationRepository.deleteById(evaluation.getId());
            }
            this.courRepository.deleteById(id);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("cour deleted",HttpStatus.OK);

    }
    public ResponseEntity<?> acheterCour(String COURSid, String Userid){

        if(COURSid==null && Userid==null ){
            return new ResponseEntity<>("null Id", HttpStatus.BAD_REQUEST);
        }

        Optional<Cour> cour= this.courRepository.findById(COURSid);
        if(cour.isPresent()){
            List<String> lists = cour.get().getListIdEtudiants();
            lists.add(Userid);
            Cour courUpdated = new Cour(
                    cour.get().getCategorieCour(),
                    cour.get().getTypeCour(),
                    cour.get().getNomCour(),
                    cour.get().getDescriptionCour(),
                    cour.get().getPrixCour(),
                    cour.get().getId(),
                    cour.get().getEvaluation(),
                    lists,
                    cour.get().getIdEnseignant()
            );
            courRepository.save(courUpdated);
            return new ResponseEntity<>("user "+Userid+" buyed cour "+COURSid+" successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("cour doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
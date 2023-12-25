package com.example.APIcour.Service;

import com.example.APIcour.Model.Cour;
import com.example.APIcour.Model.Evaluation;
import com.example.APIcour.Repository.CourRepository;
import com.example.APIcour.Repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourService {
    @Autowired
    CourRepository courRepository;
    @Autowired
    EvaluationRepository evaluationRepository;

    public Cour save(Cour cour){
        if(cour!=null && cour.getEvaluation()!=null ){
            Evaluation evaluation= cour.getEvaluation();
            Evaluation ajouterEvaluation= evaluationRepository.save(evaluation);
            cour.setEvaluation(ajouterEvaluation);
            return courRepository.save(cour);
        }
        else{
            throw new IllegalArgumentException("cour or quiz cannot be null");
        }
    }

    public Cour update(Cour cour) {
        if(cour!=null && cour.getEvaluation()!=null){
            Evaluation evaluation = cour.getEvaluation();
            Evaluation ajouterEvaluation = evaluationRepository.save(evaluation);
            cour.setEvaluation(ajouterEvaluation);
            return this.courRepository.save(cour);
        }
        else{
            throw new IllegalArgumentException("address cannot be null");
        }
    }

    public void Delete(String id  ){
        Optional<Cour> optionalCour=this.courRepository.findById(id);
        if(optionalCour.isPresent()){
            Cour cour=optionalCour.get();
            Evaluation evaluation=cour.getEvaluation();
            if (evaluation!=null){
                evaluationRepository.deleteById(evaluation.getId());
            }
            this.courRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException(" not found with ID: " + id);
        }
    }
    public String acheterCour(String COURSid, String Userid){
        Optional<Cour> cour=courRepository.findById(COURSid);
        List lists= cour.get().getListIdEtudiants();
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
        return "viva";
    }
}
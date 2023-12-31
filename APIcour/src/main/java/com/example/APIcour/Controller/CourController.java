package com.example.APIcour.Controller;


import com.example.APIcour.Model.Cour;
import com.example.APIcour.Service.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourController {
    @Autowired
    CourService courService;
    private Cour cour;
    @PostMapping("/addCour")
    public ResponseEntity<?> ajouterCour(@RequestBody Cour cour){
        return courService.save(cour);
    }
    @PutMapping("/updateCour/{id}")
    public ResponseEntity<?> updateCour(@RequestBody Cour cour,@PathVariable String id){
        return courService.update(cour,id);
    }
    @DeleteMapping("/deleteCour/{id}")
    public ResponseEntity<?> deleteCour(@PathVariable String id ){
        return this.courService.Delete(id);
    }
    @PutMapping ("/acheterCour/{Userid}/{COURSid}")
    public ResponseEntity<?> acheterCour( @PathVariable String COURSid,@PathVariable String Userid){
        return this.courService.acheterCour(COURSid,Userid);
    }


}
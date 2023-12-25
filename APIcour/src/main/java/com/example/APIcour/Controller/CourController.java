package com.example.APIcour.Controller;


import com.example.APIcour.Model.Cour;
import com.example.APIcour.Service.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourController {
    @Autowired
    CourService courService;
    private Cour cour;
    @PostMapping("/addCour")
    public Cour ajouterCour(@RequestBody Cour cour){
        return courService.save(cour);
    }
    @PutMapping("/updateCour")
    public Cour updateCour(@RequestBody Cour cour){
        return courService.update(cour);
    }
    @DeleteMapping("/deleteCour/{id}")
    public void deleteCour(@PathVariable String id ){
        this.courService.Delete(id);
    }
    @PutMapping ("/acheterCour/{Userid}/{COURSid}")
    public String acheterCour(@PathVariable String Userid,@PathVariable String COURSid){
        this.courService.acheterCour(Userid,COURSid);
        return "good";
    }
}
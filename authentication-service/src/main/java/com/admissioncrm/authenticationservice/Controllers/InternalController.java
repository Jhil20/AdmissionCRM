package com.admissioncrm.authenticationservice.Controllers;

import com.admissioncrm.authenticationservice.Services.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalController {

    @Autowired
    InternalService internalService;

    @GetMapping("/institute")
    public ResponseEntity<?> getInstitute(@RequestParam String instituteName) {
        try{

            System.out.print(instituteName);
            return ResponseEntity.ok(internalService.getInstituteFromName(instituteName));
        }
        catch(DataAccessException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/course")
    public ResponseEntity<?> getCourse(@RequestParam String courseName) {
        try {
            return ResponseEntity.ok(internalService.getCourseFromName(courseName));
        }
        catch(DataAccessException e){
            return ResponseEntity.notFound().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String userId) {
        try {
            return ResponseEntity.ok(internalService.getUserFromId(userId));
        }
        catch (DataAccessException e){
            return ResponseEntity.notFound().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/counsellors")
    public ResponseEntity<?> getCounsellor(@RequestParam String InstituteId) {
        try{
            return ResponseEntity.ok(internalService.getCounsellorsFromInstitute(InstituteId));
        }
        catch (DataAccessException e){
            return ResponseEntity.notFound().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Unexpected Error");
        }
    }
}

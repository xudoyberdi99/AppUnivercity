package com.example.controller;

import com.example.entity.Univercity;
import com.example.payload.ApiResponse;
import com.example.payload.UnivercityDto;
import com.example.repository.AddressRepository;
import com.example.repository.UnivercityRepository;
import com.example.service.UnivercityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Univercity")
public class UnivercityController {
    @Autowired
    UnivercityService univercityService;

    @GetMapping
    public HttpEntity<?> getAllUnivercity(){
       List<Univercity> univercityList=univercityService.getAllUnivercity();
       return ResponseEntity.ok(univercityList);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByUniercityId(@PathVariable Integer id){
        Univercity univercity=univercityService.getByUniercityId(id);
        return ResponseEntity.ok(univercity);
    }
    @PostMapping
    public HttpEntity<?> addUniversity(@Valid @RequestBody UnivercityDto univercityDto){
        ApiResponse apiResponse=univercityService.addUniversity(univercityDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editUniversity(@Valid @PathVariable Integer id, @RequestBody UnivercityDto univercityDto){
        ApiResponse apiResponse=univercityService.editUniversity(id,univercityDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUnivercity(@PathVariable Integer id){
        ApiResponse apiResponse=univercityService.deleteUnivercity(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

}

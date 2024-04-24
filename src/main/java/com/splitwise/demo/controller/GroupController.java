package com.splitwise.demo.controller;

import com.splitwise.demo.entities.GroupEntity;
import com.splitwise.demo.models.GroupManagerRequest;
import com.splitwise.demo.services.GroupRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "group")
public class GroupController {
    @Autowired GroupRegistrationService groupRegistrationService;
    @PutMapping
    public ResponseEntity<? extends Object> createGroup(@RequestBody GroupManagerRequest groupManagerRequest){
        try {
            return ResponseEntity.ok(groupRegistrationService.createGroup(groupManagerRequest.getGroupName(), groupManagerRequest.getUserNames()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<? extends Object> modifyGroup(@RequestBody GroupManagerRequest groupManagerRequest){
        try {
            return ResponseEntity.ok(groupRegistrationService.addPeopleInGroup(groupManagerRequest.getGroupName(), groupManagerRequest.getUserNames()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

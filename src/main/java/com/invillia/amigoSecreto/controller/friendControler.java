package com.invillia.amigoSecreto.controller;

import com.invillia.amigoSecreto.domain.Friend;
import com.invillia.amigoSecreto.request.FriendRequest;
import com.invillia.amigoSecreto.request.FriendUpdateRequest;
import com.invillia.amigoSecreto.response.FriendResponse;
import com.invillia.amigoSecreto.service.DrawService;
import com.invillia.amigoSecreto.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class friendControler {

    @Autowired
    private FriendService friendService;

    @Autowired
    private DrawService drawService;

    @PostMapping
    public ResponseEntity save(@RequestBody FriendRequest friendRequest){
        friendService.save(friendRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody FriendUpdateRequest friendUpdateRequest, @PathVariable Long id){

        Friend friendUpdate = friendService.update(friendUpdateRequest,id);
        return new ResponseEntity(friendUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        friendService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public FriendResponse findAllId(@PathVariable Long id){
        FriendResponse friendResponse = friendService.findAllId(id);
        return friendResponse;
    }

    @GetMapping
    public List<FriendResponse> findAll(){
        return friendService.findAll();
    }

    @GetMapping("/name")
    public Friend findAllName(@RequestParam String name){
        return friendService.listName(name);
    }

}

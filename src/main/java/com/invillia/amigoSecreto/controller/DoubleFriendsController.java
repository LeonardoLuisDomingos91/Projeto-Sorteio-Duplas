package com.invillia.amigoSecreto.controller;

import com.invillia.amigoSecreto.domain.DoublesFriends;
import com.invillia.amigoSecreto.exception.DoubleFriendsOddListException;
import com.invillia.amigoSecreto.request.DoubleFriendsRequest;
import com.invillia.amigoSecreto.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/double")
public class DoubleFriendsController {

    @Autowired
    private DrawService drawService;

    @GetMapping("/{id}")
    public DoublesFriends findAllId(@PathVariable Long id) {
        DoublesFriends doublesFriends = drawService.findAllId(id);
        return doublesFriends;
    }

    @GetMapping("/sorteia")
    public ResponseEntity<DoubleFriendsRequest> sorteio() {
        DoubleFriendsRequest response = new DoubleFriendsRequest();
        try {
            response = drawService.sorteio();
            response.setMensagem("The friends were matched successfully");
            return ResponseEntity.ok().body(response);
        } catch (DoubleFriendsOddListException e) {
            response.setMensagem("The friends could not be matched");
            return ResponseEntity.badRequest().body(response);
        }
    }


}

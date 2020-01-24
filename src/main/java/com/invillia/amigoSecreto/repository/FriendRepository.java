package com.invillia.amigoSecreto.repository;

import com.invillia.amigoSecreto.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Friend findByName(String name);

    //List<Friend>findAllName(String name);
}

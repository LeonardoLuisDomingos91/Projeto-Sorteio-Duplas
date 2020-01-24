package com.invillia.amigoSecreto.service;

import com.invillia.amigoSecreto.domain.Friend;
import com.invillia.amigoSecreto.exception.FriendNotContracted;
import com.invillia.amigoSecreto.mapper.FriendMapper;
import com.invillia.amigoSecreto.repository.FriendRepository;
import com.invillia.amigoSecreto.request.FriendRequest;
import com.invillia.amigoSecreto.request.FriendUpdateRequest;
import com.invillia.amigoSecreto.response.FriendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendMapper friendMapper;

    @Transactional
    public void save(FriendRequest friendRequest){
        Friend friend = friendMapper.adapterFriend(friendRequest);
        friendRepository.save(friend);
    }

    public Friend update(FriendUpdateRequest friendUpdateRequest,long id){
        Friend friendExist = friendRepository.findById(id).orElseThrow(()-> new FriendNotContracted("Freind Not found"));
        Friend friend = friendMapper.update(friendUpdateRequest,friendExist);
        friendRepository.save(friend);
        return friend;
    }

    public void delete(Long id){
        friendRepository.deleteById(id);
    }

    public FriendResponse findAllId(Long id){
        Friend friendAll = friendRepository.findById(id).orElseThrow(()-> new FriendNotContracted("Freind Not found"));
        FriendResponse fr = friendMapper.findAllFriendId(friendAll);

        return fr;
    }

    public List<FriendResponse> findAll() {
        List<Friend> friends = friendRepository.findAll();
        return friendMapper.findAll(friends);
    }

    public Friend listName(String name){

        return friendRepository.findByName(name);
    }

    public List<Friend> buscar(){
        List<Friend> friendList = friendRepository.findAll();
        return friendList;
    }

    public List<Friend> ordenaListaPorId(){
        List<Friend> list = buscar();

        list.sort(new Comparator<Friend>() {
            @Override
            public int compare(Friend friend1, Friend friend2) {
                return Long.compare(friend1.getId(),friend2.getId());
            }
        });
        return list;
    }
}

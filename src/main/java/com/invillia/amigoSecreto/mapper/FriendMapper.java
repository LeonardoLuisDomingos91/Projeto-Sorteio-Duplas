package com.invillia.amigoSecreto.mapper;

import com.invillia.amigoSecreto.domain.Friend;
import com.invillia.amigoSecreto.request.FriendRequest;
import com.invillia.amigoSecreto.request.FriendUpdateRequest;
import com.invillia.amigoSecreto.response.FriendResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FriendMapper {

    public Friend adapterFriend(FriendRequest friendRequest){
        Friend friend = new Friend();
        friend.setName(friendRequest.getName());
        friend.setAge(friendRequest.getAge());
        return friend;
    }

    public Friend update(FriendUpdateRequest friendUpdateRequest,Friend friend){

        friend.setName(friendUpdateRequest.getName());
        friend.setAge(friendUpdateRequest.getAge());

        return friend;
    }

    public FriendResponse findAllFriendId(Friend friend){
       FriendResponse friendResponse = new FriendResponse();
       friendResponse.setName(friend.getName());
       friendResponse.setAge(friend.getAge());
       return  friendResponse;
    }

    public List<FriendResponse> findAll(List<Friend> friends){

        List<FriendResponse> friendResponseList = new ArrayList<>();
        FriendResponse friendResponse = new FriendResponse();
        for(Friend f :friends){
           friendResponse.setName(f.getName());
           friendResponse.setAge(f.getAge());
           friendResponseList.add(friendResponse);
        }

        return friendResponseList;
    }
}

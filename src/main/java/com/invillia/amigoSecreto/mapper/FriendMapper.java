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

    public Friend adapterFriend(FriendRequest friendRequest) {
        Friend friend = new Friend();
        friend.setName(friendRequest.getName());
        friend.setAge(friendRequest.getAge());
        return friend;
    }

    public Friend update(FriendUpdateRequest friendUpdateRequest, Friend friend) {

        friend.setName(friendUpdateRequest.getName());
        friend.setAge(friendUpdateRequest.getAge());

        return friend;
    }

    public FriendResponse findAllFriendId(Friend friend) {
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setId(friend.getId());
        friendResponse.setName(friend.getName());
        friendResponse.setAge(friend.getAge());
        return friendResponse;
    }

    public List<FriendResponse> findAll(List<Friend> friends) {

        List<FriendResponse> friendResponseList = new ArrayList<>();
        for (Friend f : friends) {
            FriendResponse friendResponse = new FriendResponse();

            friendResponse.setName(f.getName());
            friendResponse.setAge(f.getAge());
            friendResponse.setId(f.getId());
            friendResponseList.add(friendResponse);
        }

        return friendResponseList;
    }
}

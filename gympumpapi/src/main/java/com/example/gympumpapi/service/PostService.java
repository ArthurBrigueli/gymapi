package com.example.gympumpapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gympumpapi.entity.Friend;
import com.example.gympumpapi.entity.Post;
import com.example.gympumpapi.repository.FriendRespository;
import com.example.gympumpapi.repository.PostRepository;
import com.example.gympumpapi.repository.Status;

@Service
public class PostService {
    

    PostRepository postRepository;
    FriendRespository friendRespository;


    public PostService(PostRepository postRepository, FriendRespository friendRespository){
        this.postRepository = postRepository;
        this.friendRespository = friendRespository;
    }


    public String createPost(Post post){
        postRepository.save(post);
        return "criado";
    }


    public List<Post> getAllPost(Long idUser){
        List<Friend> friends = friendRespository.findAllBySenderIdOrReceiverIdAndStatus(idUser, idUser, Status.ACCEPTED);
        List<Long> friendIds = friends.stream()
                                      .map(friend -> friend.getSenderId().equals(idUser) ? friend.getReceiverId() : friend.getSenderId())
                                      .collect(Collectors.toList());

        return postRepository.findByIdUserIn(friendIds);
    }






}

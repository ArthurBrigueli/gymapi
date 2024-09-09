package com.example.gympumpapi.repository;

import com.example.gympumpapi.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRespository extends JpaRepository<Friend, Long> {

    List<Friend> findAllBySenderIdAndStatus(Long senderId, Status status);
    List<Friend> findAllBySenderIdOrReceiverId(Long senderId, Long receiverId);
    @Query("SELECT f FROM Friend f WHERE (f.senderId = :userId OR f.receiverId = :userId) AND f.status = :status")
    List<Friend> findFriendsByStatus(@Param("userId") Long userId, @Param("status") Status status);
}

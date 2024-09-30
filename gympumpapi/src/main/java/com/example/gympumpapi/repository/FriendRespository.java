package com.example.gympumpapi.repository;

import com.example.gympumpapi.DTO.UserSearchDTO;
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
    List<Friend> findAllBySenderIdOrReceiverIdAndStatus(Long senderId, Long receiverId, Status status);
    
    @Query("SELECT f FROM Friend f WHERE (f.senderId = :userId OR f.receiverId = :userId) AND f.status = :status")
    List<Friend> findFriendsByStatus(@Param("userId") Long userId, @Param("status") Status status);




    @Query("SELECT new com.example.gympumpapi.DTO.UserSearchDTO(u.id, u.name, f.status) " +
           "FROM User u " +
           "LEFT JOIN Friend f ON (u.id = f.senderId OR u.id = f.receiverId) " +
           "WHERE u.name LIKE CONCAT('%', :name, '%')")
    List<UserSearchDTO> findUsersAndFriendStatusByName(@Param("name") String name);

}

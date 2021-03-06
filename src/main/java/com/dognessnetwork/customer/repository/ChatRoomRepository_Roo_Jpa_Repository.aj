// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.repository;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.repository.ChatRoomRepository;
import com.dognessnetwork.customer.repository.ChatRoomRepositoryCustom;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

privileged aspect ChatRoomRepository_Roo_Jpa_Repository {
    
    declare parents: ChatRoomRepository extends DetachableJpaRepository<ChatRoom, Long>;
    
    declare parents: ChatRoomRepository extends ChatRoomRepositoryCustom;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param seat
     * @param pageable
     * @return Page
     */
    public abstract Page<ChatRoom> ChatRoomRepository.findBySeat(String seat, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param seat
     * @return Long
     */
    public abstract long ChatRoomRepository.countBySeat(String seat);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param seat
     * @param petUser
     * @param status
     * @param pageable
     * @return Page
     */
    public abstract Page<ChatRoom> ChatRoomRepository.findBySeatAndPetUserAndStatus(String seat, String petUser, RoomStatus status, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param seat
     * @param petUser
     * @param status
     * @return Long
     */
    public abstract long ChatRoomRepository.countBySeatAndPetUserAndStatus(String seat, String petUser, RoomStatus status);
    
}

package com.eComm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eComm.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email=:email")
	Optional<User> findByEmail(String email);
}

//japa repo only give inbuilt method to operation like save,findall,fingbyId,deleyte to get our own mrthod we write customm Query
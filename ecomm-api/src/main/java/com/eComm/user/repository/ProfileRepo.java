package com.eComm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eComm.user.entity.Profile;
@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
	Optional<Profile> findByUserUserId(Integer userId);

}

package com.eComm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eComm.role.enums.RoleType;
import com.eComm.user.entity.Role;

@Repository
public interface Rolerepo extends JpaRepository<Role, Integer> {
	Optional<Role> findByRoleName(RoleType roleName);

}

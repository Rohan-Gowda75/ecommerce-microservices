package com.eComm.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eComm.exception.UserException;
import com.eComm.role.enums.RoleType;
import com.eComm.user.dto.RoleDto;
import com.eComm.user.entity.Role;
import com.eComm.user.repository.Rolerepo;
import com.eComm.user.service.RoleService;

@Service
public class RolseServiceImpl implements RoleService {

    @Autowired
    private Rolerepo rrepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleDto addRole(RoleType roleName) {

        Role role = rrepo.findByRoleName(roleName).orElse(null);

        if (role != null) {
            throw new UserException(
                    "Role Already exists",
                    HttpStatus.CONFLICT
            );
        }

        role = new Role();

        role.setRoleName(roleName);

        role = rrepo.save(role);

        return mapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto getRoleByRoleName(RoleType roleName) {

        Role role = rrepo.findByRoleName(roleName)
                .orElseThrow(() ->
                        new UserException(
                                "Role not found!",
                                HttpStatus.NOT_FOUND
                        )
                );

        RoleDto dto = mapper.map(role, RoleDto.class);

        return dto;
    }

    @Override
    public RoleDto getRoleById(Integer roleId) {

        Role role = rrepo.findById(roleId)
                .orElseThrow(() ->
                        new UserException(
                                "Role not found!",
                                HttpStatus.NOT_FOUND
                        )
                );

        return mapper.map(role, RoleDto.class);
    }

    @Override
    public Role getRoleEntityByRoleName(RoleType roleName) {

        return rrepo.findByRoleName(roleName)
                .orElseThrow(() ->
                        new UserException(
                                "Role not found!",
                                HttpStatus.NOT_FOUND
                        )
                );
    }
}
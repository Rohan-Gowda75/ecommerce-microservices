package com.eComm.user.service;

import com.eComm.role.enums.RoleType;
import com.eComm.user.dto.RoleDto;
import com.eComm.user.entity.Role;

public interface RoleService {

    RoleDto addRole(RoleType roleName);

    RoleDto getRoleByRoleName(RoleType roleName);

    RoleDto getRoleById(Integer roleId);

    Role getRoleEntityByRoleName(RoleType roleName);
}
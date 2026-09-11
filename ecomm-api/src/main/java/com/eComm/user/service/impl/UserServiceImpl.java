package com.eComm.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eComm.exception.UserException;
import com.eComm.user.dto.ProfileDto;
import com.eComm.user.dto.RoleDto;
import com.eComm.user.dto.UserDto;
import com.eComm.user.entity.Profile;
import com.eComm.user.entity.Role;
import com.eComm.user.entity.User;
import com.eComm.user.repository.UserRepo;
import com.eComm.user.request.LoginRequest;
import com.eComm.user.request.RegisterRequest;
import com.eComm.user.request.UpdateRequest;
import com.eComm.user.service.EmailService;
import com.eComm.user.service.ProfileService;
import com.eComm.user.service.RoleService;
import com.eComm.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ProfileService pservice;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;

    @Transactional
    @Override
    public UserDto register(RegisterRequest request) {

        User alreadyExists = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (alreadyExists != null) {

            throw new UserException(
                    "User already exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        // RegisterRequest -> User Entity
        User user = mapper.map(request, User.class);

        // Get Role Entity using roleName from RegisterRequest
        Role role = roleService
                .getRoleEntityByRoleName(request.getRoleName());

        // Set Role into User
        user.setRole(role);

        // Save User
        user = userRepository.save(user);

        // RegisterRequest -> Profile Entity
        Profile profile = mapper.map(request, Profile.class);

        // Set User in Profile
        profile.setUser(user);

        // Save Profile
        ProfileDto profileDto = pservice.addProfile(profile);

        // Send registration email
        emailService.sendRegistrationMail(
                user.getEmail(),
                profile.getFirstName()
        );

        // User Entity -> UserDto
        UserDto dto = mapper.map(user, UserDto.class);

        // Add ProfileDto
        dto.setProfileDto(profileDto);

        // Add RoleDto
        RoleDto roleDto =
                mapper.map(user.getRole(), RoleDto.class);

        dto.setRoleDto(roleDto);

        return dto;
    }

    @Override
    public UserDto login(LoginRequest request) {

        User exists = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (exists == null) {

            throw new UserException(
                    "User not found",
                    HttpStatus.NOT_FOUND
            );
        }

        if (!exists.getPassword().equals(request.getPassword())) {

            throw new UserException(
                    "Invalid Password",
                    HttpStatus.BAD_REQUEST
            );
        }

        // User Entity -> UserDto
        UserDto dto = mapper.map(exists, UserDto.class);

        // Get Profile
        ProfileDto profileDto =
                pservice.getProfileByUserId(exists.getUserId());

        dto.setProfileDto(profileDto);

        // Get Role
        if (exists.getRole() != null) {

            RoleDto roleDto =
                    mapper.map(exists.getRole(), RoleDto.class);

            dto.setRoleDto(roleDto);
        }

        return dto;
    }

    @Override
    public UserDto getById(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserException(
                                "User not found",
                                HttpStatus.NOT_FOUND
                        )
                );

        // User Entity -> UserDto
        UserDto dto = mapper.map(user, UserDto.class);

        // Get Profile
        ProfileDto profileDto =
                pservice.getProfileByUserId(userId);

        dto.setProfileDto(profileDto);

        // Get Role
        if (user.getRole() != null) {

            RoleDto roleDto =
                    mapper.map(user.getRole(), RoleDto.class);

            dto.setRoleDto(roleDto);
        }

        return dto;
    }

    @Override
    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {

            throw new UserException(
                    "No users found",
                    HttpStatus.NOT_FOUND
            );
        }

        return users.stream()
                .map(user -> {

                    // User Entity -> UserDto
                    UserDto dto =
                            mapper.map(user, UserDto.class);

                    // Get Role
                    if (user.getRole() != null) {

                        RoleDto roleDto =
                                mapper.map(
                                        user.getRole(),
                                        RoleDto.class
                                );

                        dto.setRoleDto(roleDto);
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public void updateUser(Integer userId, UpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserException(
                                "User not found",
                                HttpStatus.NOT_FOUND
                        )
                );

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserException(
                                "User not found",
                                HttpStatus.NOT_FOUND
                        )
                );

        userRepository.delete(user);
    }
}
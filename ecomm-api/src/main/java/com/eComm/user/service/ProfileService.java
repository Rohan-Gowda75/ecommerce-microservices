package com.eComm.user.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eComm.user.dto.ProfileDto;
import com.eComm.user.entity.Profile;
import com.eComm.user.request.ProfileUpdateRequest;


public interface ProfileService {


	ProfileDto addProfile(Profile profile);


	ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request, MultipartFile image);


	void deleteProfile(Integer profileId);


	ProfileDto findById(Integer profileId);


	ProfileDto getProfileByUserId(Integer userId);


	List<Profile> findAll();

}
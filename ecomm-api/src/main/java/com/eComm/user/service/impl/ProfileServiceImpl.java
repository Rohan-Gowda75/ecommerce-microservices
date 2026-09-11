package com.eComm.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eComm.exception.UserException;
import com.eComm.user.dto.ProfileDto;
import com.eComm.user.entity.Profile;
import com.eComm.user.repository.ProfileRepo;
import com.eComm.user.request.ProfileUpdateRequest;
import com.eComm.user.response.CloudinaryResponse;
import com.eComm.user.service.CloudinaryService;
import com.eComm.user.service.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService {


    @Autowired
    private ProfileRepo prepo;


    @Autowired
    private ModelMapper mapper;
    
	@Autowired
	private CloudinaryService cservice;



    @Override
    public ProfileDto addProfile(Profile profile) {


        Profile savedProfile = prepo.save(profile);


        return mapper.map(savedProfile, ProfileDto.class);
    }



//
//    @Override
//    public void updateProfile(Integer profileId, Profile profile) {
//
//
//        Profile existingProfile = prepo.findById(profileId)
//                .orElseThrow(() ->
//                new UserException(
//                        "Profile not found",
//                        HttpStatus.NOT_FOUND
//                ));
//
//
//
//        existingProfile.setFirstName(profile.getFirstName());
//
//        existingProfile.setLastName(profile.getLastName());
//
//        existingProfile.setPhoneNo(profile.getPhoneNo());
//
//        existingProfile.setDOB(profile.getDOB());
//
//        existingProfile.setUrl(profile.getUrl());
//
//
//
//        prepo.save(existingProfile);
//
//    }




    @Override
    public void deleteProfile(Integer profileId) {


        Profile profile = prepo.findById(profileId)
                .orElseThrow(() ->
                new UserException(
                        "Profile not found",
                        HttpStatus.NOT_FOUND
                ));



        prepo.delete(profile);

    }




    @Override
    public ProfileDto findById(Integer profileId) {


        Profile profile = prepo.findById(profileId)
                .orElseThrow(() ->
                new UserException(
                        "Profile not found",
                        HttpStatus.NOT_FOUND
                ));



        return mapper.map(profile, ProfileDto.class);

    }





    @Override
    public ProfileDto getProfileByUserId(Integer userId) {


        Profile profile = prepo.findByUserUserId(userId)
                .orElseThrow(() ->
                new UserException(
                        "Profile not found for user",
                        HttpStatus.NOT_FOUND
                ));



        return mapper.map(profile, ProfileDto.class);

    }





    @Override
    public List<Profile> findAll() {


        List<Profile> profiles = prepo.findAll();


        if(profiles.isEmpty()) {

            throw new UserException(
                    "No profiles found",
                    HttpStatus.NOT_FOUND
            );
        }


        return profiles;

    }



	@Override
	public ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request, MultipartFile image) {
		
		Profile p = prepo.findById(profileId).orElseThrow(()-> new UserException("profile not found", HttpStatus.NOT_FOUND));
		
		mapper.map(request, p);
		if(image!=null&&!image.isEmpty()) {
			if(p.getImageUrl()!=null&&p.getPublicUrl()!=null) {
				cservice.deleteImage(p.getImageUrl());
				
			}
			
			CloudinaryResponse response = cservice.uploadImage(image);
			mapper.map(response, p);
			
		}
		p=prepo.save(p);
		
		
		
		return mapper.map(p, ProfileDto.class);
	}	

}
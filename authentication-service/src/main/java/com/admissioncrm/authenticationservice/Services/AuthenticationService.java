package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.Jwt.JwtResponse;
import com.admissioncrm.authenticationservice.DTO.LoginRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.DTO.student.StudentLoginRequest;
import com.admissioncrm.authenticationservice.DTO.student.StudentRegistrationRequest;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;

import com.admissioncrm.authenticationservice.Entities.CounsellorDetails;
import com.admissioncrm.authenticationservice.Entities.InstituteAdminDetails;
import com.admissioncrm.authenticationservice.ExceptionHandling.ApiException;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import com.admissioncrm.authenticationservice.Utilities.JwtUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CounsellorRepository counsellorRepository;
    @Autowired
    InstituteAdminRepository instituteAdminRepository;

    @Autowired
    JwtUtils   jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<JwtResponse> loginUser(@Valid LoginRequest loginRequest) {
        String identifier = loginRequest.getIdentifier();
        String password = loginRequest.getPassword();
        //fetch the user from DB

        User user =getUserByIdentifier(loginRequest.getIdentifier());

        //authenticate using spring security
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));
        }catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("Invalid credentials");
        }

        //generate the token
        try{
            String jwtToken = jwtUtils.generateToken(user.getUsername(), user.getRole(), user.getId());
            return ResponseEntity.ok(new JwtResponse(jwtToken, user.getRole()));
        }catch(Exception e)
        {
            throw new ApiException("Failed to generate JWT token");
        }
    }



    //register Student

    @Transactional
    public JwtResponse registerStudent(StudentRegistrationRequest request) {

        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new ApiException("Mobile number already registered");
        }
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new ApiException("Email already registered");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setMobileNumber(request.getMobileNumber());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);

        user = userRepository.save(user);
        //Also add save the Student Details Entity in future for more user specific details


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMobileNumber(), request.getPassword())
        );

        String jwtToken = jwtUtils.generateToken(user.getUsername(), Role.STUDENT, user.getId());
        return new JwtResponse(jwtToken, Role.STUDENT);


    }

    @Transactional
    public Optional<?> getProfileDetailsByRole(String userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
        {
            throw new ApiException("User not found");
        }
        return getUserDetailFromRoles(user.get().getRole(), userId);
    }

    @Transactional
    public Optional<?> UpdateUserDetails(ListResponseDTO listResponseDTO) throws UsernameAlreadyExistsException {

        switch(userRepository.getRoleFromId(listResponseDTO.getId()))
        {
            case STUDENT :
                Optional<User> existedUser = userRepository.findById(listResponseDTO.getId());
                if (existedUser.isEmpty())
                {
                    throw new ApiException("User not found");
                }
                existedUser.get().setFirstName(listResponseDTO.getFirstName());
                existedUser.get().setLastName(listResponseDTO.getLastName());
                if(userRepository.existsByUsernameAndIdNot(listResponseDTO.getUsername(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Username already exists");
                }
                existedUser.get().setUsername(listResponseDTO.getUsername());
                if (userRepository.existsByMobileNumberAndIdNot(listResponseDTO.getMobileNumber(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("MobileNumber already exists");
                }
                if(userRepository.existsByEmailAndIdNot(listResponseDTO.getEmail(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Email already exists");
                }
                existedUser.get().setMobileNumber(listResponseDTO.getMobileNumber());
                existedUser.get().setEmail(listResponseDTO.getEmail());

                return Optional.of(userRepository.save(existedUser.get()));
            case COUNSELLOR:
                Optional<User> existedCounsellor = userRepository.findById(listResponseDTO.getId());
                if (existedCounsellor.isEmpty())
                {
                    throw new ApiException("User not found");
                }

                //user updation
                existedCounsellor.get().setFirstName(listResponseDTO.getFirstName());
                existedCounsellor.get().setLastName(listResponseDTO.getLastName());
                if(userRepository.existsByUsernameAndIdNot(listResponseDTO.getUsername(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Username already exists");
                }
                existedCounsellor.get().setUsername(listResponseDTO.getUsername());
                if (userRepository.existsByMobileNumberAndIdNot(listResponseDTO.getMobileNumber(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("MobileNumber already exists");
                }
                if(userRepository.existsByEmailAndIdNot(listResponseDTO.getEmail(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Email already exists");
                }
                existedCounsellor.get().setMobileNumber(listResponseDTO.getMobileNumber());
                existedCounsellor.get().setEmail(listResponseDTO.getEmail());

                //counsellor updation
                CounsellorDetails counsellor = existedCounsellor.get().getCouncellorId();
                counsellor.setBio(listResponseDTO.getBio());
                counsellor.setSpecialization(listResponseDTO.getSpecialization());

                existedCounsellor.get().setCouncellorId(counsellor);
                counsellor.setUserId(existedCounsellor.get());

                User updatedUserC = userRepository.save(existedCounsellor.get());
                CounsellorDetails updatedCounsellor = counsellorRepository.save(counsellor);

                ListResponseDTO responseDTOc = new ListResponseDTO(
                        updatedUserC.getFirstName(),
                        updatedUserC.getLastName(),
                        updatedUserC.getUsername(),
                        updatedUserC.getEmail(),
                        updatedUserC.getMobileNumber(),
                        updatedCounsellor.getSpecialization(),
                        updatedCounsellor.getBio(),
                        null,
                        null
                );
                return Optional.of(responseDTOc);

            case INSTITUTE_ADMIN:
                Optional<User> existedInstituteAdmin = userRepository.findById(listResponseDTO.getId());
                if (existedInstituteAdmin.isEmpty())
                {
                    throw new ApiException("User not found");
                }

                existedInstituteAdmin.get().setFirstName(listResponseDTO.getFirstName());
                existedInstituteAdmin.get().setLastName(listResponseDTO.getLastName());
                if(userRepository.existsByUsernameAndIdNot(listResponseDTO.getUsername(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Username already exists");
                }
                existedInstituteAdmin.get().setUsername(listResponseDTO.getUsername());
                if (userRepository.existsByMobileNumberAndIdNot(listResponseDTO.getMobileNumber(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("MobileNumber already exists");
                }
                if(userRepository.existsByEmailAndIdNot(listResponseDTO.getEmail(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Email already exists");
                }
                existedInstituteAdmin.get().setMobileNumber(listResponseDTO.getMobileNumber());
                existedInstituteAdmin.get().setEmail(listResponseDTO.getEmail());

                InstituteAdminDetails instituteAdmin = existedInstituteAdmin.get().getInstituteAdminId();
                instituteAdmin.setDesignation(listResponseDTO.getDesignation());
                instituteAdmin.setAlternateEmail(listResponseDTO.getAlternateEmail());

                existedInstituteAdmin.get().setInstituteAdminId(instituteAdmin);
                instituteAdmin.setUserId(existedInstituteAdmin.get());

                User updatedUser = userRepository.save(existedInstituteAdmin.get());
                InstituteAdminDetails updatedInstituteAdmin = instituteAdminRepository.save(instituteAdmin);

                ListResponseDTO responseDTOi = new ListResponseDTO(
                        updatedUser.getFirstName(),
                        updatedUser.getLastName(),
                        updatedUser.getUsername(),
                        updatedUser.getEmail(),
                        updatedUser.getMobileNumber(),
                        null,
                        null,
                        updatedInstituteAdmin.getDesignation(),
                        updatedInstituteAdmin.getAlternateEmail()
                );
                return Optional.of(responseDTOi);

            case UNIVERSITY_ADMIN:
                Optional<User> existedAdmin = userRepository.findById(listResponseDTO.getId());
                if (existedAdmin.isEmpty())
                {
                    throw new ApiException("User not found");
                }
                existedAdmin.get().setFirstName(listResponseDTO.getFirstName());
                existedAdmin.get().setLastName(listResponseDTO.getLastName());
                if(userRepository.existsByUsernameAndIdNot(listResponseDTO.getUsername(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Username already exists");
                }
                existedAdmin.get().setUsername(listResponseDTO.getUsername());
                if (userRepository.existsByMobileNumberAndIdNot(listResponseDTO.getMobileNumber(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("MobileNumber already exists");
                }
                if(userRepository.existsByEmailAndIdNot(listResponseDTO.getEmail(),listResponseDTO.getId()))
                {
                    throw new UsernameAlreadyExistsException("Email already exists");
                }
                existedAdmin.get().setMobileNumber(listResponseDTO.getMobileNumber());
                existedAdmin.get().setEmail(listResponseDTO.getEmail());

                return Optional.of(userRepository.save(existedAdmin.get()));
            default:
                throw new ApiException("Invalid role");
        }
    }




    // Utility methods
    private User getUserByIdentifier(String identifier) {
        if (identifier.matches("^\\d{10}$")) {
            return userRepository.findByMobileNumber(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Mobile number not found"));
        } else if (identifier.contains("@")) {
            return userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        } else {
            return userRepository.findByUsername(identifier)
                    .orElseThrow(()->new  UsernameNotFoundException("Username not found"));
        }
    }

    private Optional<?> getUserDetailFromRoles(Role role,String userId)
    {
        switch (role)
        {
            case STUDENT:
                Optional<User> existedUser =  userRepository.findById(userId);
                if (existedUser.isEmpty())
                {
                    throw new ApiException("User not found");
                }
                UserCreateRequest user = new UserCreateRequest();
                user.setUsername(existedUser.get().getUsername());
                user.setFirstName(existedUser.get().getFirstName());
                user.setLastName(existedUser.get().getLastName());
                user.setEmail(existedUser.get().getEmail());
                user.setMobileNumber(existedUser.get().getMobileNumber());
                return Optional.of(user);
            case UNIVERSITY_ADMIN:
                Optional<User> existedAdmin = userRepository.findByRole(role);
                if (existedAdmin.isEmpty())
                {
                    throw new ApiException("User not found");
                }
                UserCreateRequest admin = new UserCreateRequest();
                admin.setUsername(existedAdmin.get().getUsername());
                admin.setFirstName(existedAdmin.get().getFirstName());
                admin.setLastName(existedAdmin.get().getLastName());
                admin.setEmail(existedAdmin.get().getEmail());
                admin.setMobileNumber(existedAdmin.get().getMobileNumber());
                return Optional.of(admin);
            case INSTITUTE_ADMIN:
                return userRepository.getInstituteAdminById(userId);
            case COUNSELLOR:
                return userRepository.getCounsellorById(userId);
            default:
                throw new ApiException("Invalid role");
        }
    }
}
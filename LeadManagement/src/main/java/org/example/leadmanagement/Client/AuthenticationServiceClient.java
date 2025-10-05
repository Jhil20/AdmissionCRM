package org.example.leadmanagement.Client;

import org.example.leadmanagement.DTO.InternalDTO.CourseResponse;
import org.example.leadmanagement.DTO.InternalDTO.InstituteResponse;
import org.example.leadmanagement.DTO.InternalDTO.UserResponse;
import org.example.leadmanagement.Exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;

@Component
public class AuthenticationServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    private HttpEntity<Void> createRequestEntityWithToken(String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);// Add token to header
        return new HttpEntity<>(headers);
    }


    public InstituteResponse fetchInstitute(String instituteName,String token)
    {
        String url = "http://localhost:8000/api/internal/institute?instituteName={instituteName}";
        HttpEntity<Void> requestEntity = createRequestEntityWithToken(token);
        ResponseEntity<InstituteResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                InstituteResponse.class,
                instituteName
        );

        if(response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }

        throw new ResourceNotFound("Institute not found");
    }

    public CourseResponse fetchCourse(String courseName,String token)
    {
        String url = "http://localhost:8000/api/internal/course?courseName={courseName}";
        HttpEntity<Void> requestEntity = createRequestEntityWithToken(token);
        ResponseEntity<CourseResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                CourseResponse.class,
                courseName
        );

        if (response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }

        throw new ResourceNotFound("Course not found");
    }

    public UserResponse fetchUser(String userId,String token)
    {
        String url = "http://localhost:8000/api/internal/user?userId={userId}";
        HttpEntity<Void> requestEntity = createRequestEntityWithToken(token);
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                UserResponse.class,
                userId
        );
        if (response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }

        throw new ResourceNotFound("User not found");
    }
}

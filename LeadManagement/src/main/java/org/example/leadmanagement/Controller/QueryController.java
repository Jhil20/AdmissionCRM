package org.example.leadmanagement.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.leadmanagement.DTO.Query.Request.QueryCreateDTOReq;
import org.example.leadmanagement.Exception.QueryException;
import org.example.leadmanagement.Service.QueryService;
import org.example.leadmanagement.Utils.ErrorResponse;
import org.example.leadmanagement.Utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createQuery(@Valid @RequestBody QueryCreateDTOReq queryCreateDTOReq, @RequestHeader("Authorization") String token, HttpServletRequest request)
    {
        try {
            token = token.startsWith("Bearer ") ? token.substring(7) : token;
            String userId = (String) request.getAttribute("userId");
            System.out.println("Institute id : "+queryCreateDTOReq.getInstituteId());
            System.out.println("admission inquiry id : "+queryCreateDTOReq.getAdmissionInquiryId());
            String queryId = queryService.createQuery(queryCreateDTOReq,userId,token);
            SuccessResponse successResponse = new SuccessResponse(
                    LocalDateTime.now(),
                    HttpStatus.OK.value(),
                    "Query created Successfully",
                    Map.of("queryId",queryId)
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        catch(QueryException e){
            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage()
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.admissioncrm.authenticationservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    @NotBlank(message = "identifeir should not be blank")
    String identifier;

    @NotNull
    @NotBlank(message = "password should not be blank")
    String password;

    public @NotNull @NotBlank(message = "identifeir should not be blank") String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NotNull @NotBlank(message = "identifeir should not be blank") String identifier) {
        this.identifier = identifier;
    }

    public @NotNull @NotBlank(message = "password should not be blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotBlank(message = "password should not be blank") String password) {
        this.password = password;
    }
}

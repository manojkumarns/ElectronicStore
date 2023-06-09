package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.ImageNameValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    @Size(min = 3 , max = 20, message = "Invalid Name!!")
    private String name;
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "invalid user email !!")
    @Email(message = "Invalid user Email")
    @NotBlank(message = "email is required !!")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @Size(min = 4 , max = 6 , message = "invalid gender")
    private String gender;
    @NotBlank(message = "write something about your self")
    private String about;
    @ImageNameValid
    private String image;
}

package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entites.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDto createUSer(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId) throws IOException;

    PageableResponse<UserDto> getAllUser(int pageNumber , int pageSize , String sortBy , String sortDir);

    //get single user
    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto>  searchUSer(String keyWord);

}

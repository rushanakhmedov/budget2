package com.budget.budget2.mapper;

import com.budget.budget2.dto.UserDTO;
import com.budget.budget2.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO fromUserToUserDTO(User user);

    List<UserDTO> fromUserListToUserDTOList(List<User> userList);
}

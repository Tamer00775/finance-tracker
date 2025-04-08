package kz.sdu.finance_tracker.mapper;

import kz.sdu.finance_tracker.dto.UserDto;
import kz.sdu.finance_tracker.dto.UserRegistrationDto;
import kz.sdu.finance_tracker.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegistrationDto userRegistrationDto);

    UserRegistrationDto toDto(User user);

    UserDto toUserDto(User user);
}

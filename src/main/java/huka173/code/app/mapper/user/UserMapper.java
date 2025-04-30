package huka173.code.app.mapper.user;

import huka173.code.app.dto.user.UserCreateDTO;
import huka173.code.app.dto.user.UserDTO;
import huka173.code.app.dto.user.UserUpdateDTO;
import huka173.code.app.mapper.JsonNullableMapper;
import huka173.code.app.model.User;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class },
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserMapper {
    public abstract User map(UserCreateDTO dto);
    public abstract UserDTO map(User model);
    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);
}

package de.campuscoffee.api.mapper;

import de.campuscoffee.api.dto.UserDto;
import de.campuscoffee.domain.model.User;

/**
 * Mapper zwischen API-DTO und Domain-Model.
 */
public final class UserMapper {

    private UserMapper() {}

    public static User toDomain(UserDto dto) {
        if (dto == null) return null;
        User user;
        if (dto.getId() == null) {
            user = User.createNew(dto.getLogin(), dto.getEmail(), dto.getFirstName(), dto.getLastName());
        } else {
            user = new User();
            user.setId(dto.getId());
            user.setCreatedAt(dto.getCreatedAt());
            user.setUpdatedAt(dto.getUpdatedAt());
            user.setLogin(dto.getLogin());
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
        }
        return user;
    }

    public static UserDto toDto(User domain) {
        if (domain == null) return null;
        UserDto dto = new UserDto();
        dto.setId(domain.getId());
        dto.setCreatedAt(domain.getCreatedAt());
        dto.setUpdatedAt(domain.getUpdatedAt());
        dto.setLogin(domain.getLogin());
        dto.setEmail(domain.getEmail());
        dto.setFirstName(domain.getFirstName());
        dto.setLastName(domain.getLastName());
        return dto;
    }
}
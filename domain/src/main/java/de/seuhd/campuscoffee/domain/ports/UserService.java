package de.seuhd.campuscoffee.domain.ports;

import org.springframework.lang.NonNull;
import de.seuhd.campuscoffee.domain.model.User;
import java.util.List;

public interface UserService {
    //TODO: Define user service interface
    void clear();

    @NonNull
    List<User> getAll();

    @NonNull
    User getById(@NonNull Long id);

    @NonNull
    User getByLoginName(@NonNull String loginName);

    @NonNull
    User upsert(@NonNull User user);

    void delete(@NonNull Long id);
}

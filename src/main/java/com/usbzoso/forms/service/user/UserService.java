package com.usbzoso.forms.service.user;

import java.util.Collection;
import java.util.Optional;

import com.usbzoso.forms.domain.user.UserCreateForm;
import com.usbzoso.forms.entities.User;

public interface UserService {

    Optional<User> getUserById(long id);
    Optional<User> getUserByUsername(String username);
    Collection<User> getAllUsers();
    User create(UserCreateForm form);
}

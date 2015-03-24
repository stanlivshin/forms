package com.usbzoso.forms.service.currentuser;

import org.springframework.stereotype.Service;

import com.usbzoso.forms.domain.user.CurrentUser;
import com.usbzoso.forms.domain.user.UserRole;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == UserRole.ADMIN || currentUser.getId().equals(userId));
    }

}
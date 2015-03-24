package com.usbzoso.forms.service.currentuser;

import com.usbzoso.forms.domain.user.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
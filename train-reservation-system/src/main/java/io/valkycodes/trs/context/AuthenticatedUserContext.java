package io.valkycodes.trs.context;

import io.valkycodes.trs.model.dto.UserData;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class AuthenticatedUserContext {
    private boolean isAuthenticated;
    private UserData userData;

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void authenticateUser(UserData userData) {
        this.isAuthenticated = true;
        this.userData = userData;
    }

    public void resetUser() {
        this.isAuthenticated = false;
        this.userData = null;
    }

    public UserData getUserData() {
        return userData;
    }
}

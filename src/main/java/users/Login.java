package users;

import users.dao.UserDaoImpl;

import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.util.Map;

public class Login implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean loginSucceeded = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("email");
        PasswordCallback passwordCallback = new PasswordCallback("password", false);
        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            String email = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());

            User user = new UserDaoImpl().getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                loginSucceeded = true;
                return true;
            } else {
                throw new LoginException("Identifiants incorrects");
            }
        } catch (Exception e) {
            throw new LoginException("Erreur d'authentification : " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return loginSucceeded;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        loginSucceeded = false;
        return true;
    }
}

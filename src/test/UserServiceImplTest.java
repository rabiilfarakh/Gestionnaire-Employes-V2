import enumeration.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import users.User;
import users.dao.UserDAO;
import users.service.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new User("Jane Doe", "jane@example.com", BCrypt.hashpw("password123", BCrypt.gensalt()), "1234567890", new Date(), "CIN123", Role.Candidat);
    }

    @Test
    void testSaveUser() {
        userService.saveUser(user);
        verify(userDAO).saveUser(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(user);
        when(userDAO.getAllUsers()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }

    @Test
    void testGetUserById() {
        when(userDAO.getUserById(userId)).thenReturn(user);

        User foundUser = userService.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    void testUpdateUser() {
        userService.updateUser(user);
        verify(userDAO).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(userId);
        verify(userDAO).deleteUser(userId);
    }

    @Test
    void testLoginSuccessful() {
        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);

        User loggedInUser = userService.login(user.getEmail(), "password123"); // Utiliser le mot de passe en clair

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    void testLoginFailed() {
        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(null);

        User loggedInUser = userService.login(user.getEmail(), "wrongPassword");

        assertNull(loggedInUser);
    }

    @Test
    void testGetUserByEmail() {
        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);

        User foundUser = userService.getUserByEmail(user.getEmail());

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }
}

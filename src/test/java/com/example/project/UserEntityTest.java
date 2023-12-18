package com.example.project;

import com.example.project.entity.Account;
import com.example.project.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UserEntityTest {
    @Test
    public void testUserEntity() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");

        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testRemovingUserFromAccount() {
        User user = new User();
        user.setName("Alice");

        Account account = new Account();
        account.setName("Regular Account");

        user.addAccounts(account);

        List<User> usersInAccountBeforeRemoval = account.getUsers();
        assertTrue(usersInAccountBeforeRemoval.contains(user));

        user.removeAccount(account);

        List<User> usersInAccountAfterRemoval = account.getUsers();
        assertFalse(usersInAccountAfterRemoval.contains(user));
    }
}

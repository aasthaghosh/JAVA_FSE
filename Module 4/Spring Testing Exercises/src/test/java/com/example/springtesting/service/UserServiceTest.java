package com.example.springtesting.service;

import com.example.springtesting.model.User;
import com.example.springtesting.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Exercise 2: Mocking a Repository in a Service Test
    @Test
    public void testGetUserByIdSuccess() {
        // Arrange
        User user = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    // Exercise 6: Test Service Exception Handling
    @Test
    public void testGetUserByIdNotFoundThrowsException() {
        // Arrange
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(2L);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(2L);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User(null, "Bob");
        User savedUser = new User(1L, "Bob");
        when(userRepository.save(user)).thenReturn(savedUser);

        // Act
        User result = userService.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Bob", result.getName());
        verify(userRepository, times(1)).save(user);
    }
}

package com.example.springtesting.repository;

import com.example.springtesting.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        // Arrange
        User user1 = new User(null, "Charlie");
        User user2 = new User(null, "Delta");
        User user3 = new User(null, "Charlie");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // Act
        List<User> results = userRepository.findByName("Charlie");

        // Assert
        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(u -> u.getName().equals("Charlie")));
    }
}

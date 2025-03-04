package com.company.jmixpmflowbase.unit;

import com.company.jmixpmflowbase.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserUnitTest {

    @Test
    @DisplayName("Checks the computation of the User instance name")
    void testInstanceName() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("j_doe");

        Assertions.assertEquals("John Doe [j_doe]", user.getDisplayName());
    }
}

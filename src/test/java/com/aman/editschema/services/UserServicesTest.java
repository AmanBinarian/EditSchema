package com.aman.editschema.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aman.editschema.model.User;
import com.aman.editschema.model.UserSchema;
import com.aman.editschema.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @InjectMocks
    private UserServices userServices;

    @Mock
    private UserRepo userRepo;


    @Test
    void testSaveUser() {
    	System.out.println("Save user test run ");
        User user = new User();
        user.setUser_id(101);
        user.setUser_name("Aman Rathore");
        user.setUser_email("amanrathore@gmail.com");


        UserSchema userSchema = new UserSchema();
        userSchema.setUser_id(101);
        userSchema.setUser_name("Aman Rathore");
        userSchema.setUser_email("amanrathore@gmail.com");

        when(userRepo.save(any(UserSchema.class))).thenReturn(userSchema);

        userServices.saveUser(user);

        verify(userRepo, times(1)).save(any(UserSchema.class));
    }

    @Test
    void testGetUserById() {
    	System.out.println("Get user By iD test run ");
        UserSchema userSchema = new UserSchema();
        userSchema.setUser_id(101);
        userSchema.setUser_name("Aman Rathore");
        userSchema.setUser_email("amanrathore@gmail.com");

        when(userRepo.findById(1)).thenReturn(Optional.of(userSchema));

        UserSchema result = userServices.getUserById(1);

        assertNotNull(result);
        assertEquals(101, result.getUser_id());
        assertEquals("Aman Rathore", result.getUser_name());
        assertEquals("amanrathore@gmail.com", result.getUser_email());
    }

    @Test
    void testGetAllUsers() {
    	System.out.println("Get all userss test run ");
        List<UserSchema> userList = List.of(new UserSchema(), new UserSchema());
        when(userRepo.findAll()).thenReturn(userList);

        List<UserSchema> result = userServices.getAllUser();

        assertEquals(2, result.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
    	System.out.println("update user test run ");
        User user = new User();
        user.setUser_name("Himanshu Rathore");
        user.setUser_email("himanshurathore@gmail.com");

        UserSchema existingUser = new UserSchema();
        existingUser.setUser_id(101);
        existingUser.setUser_name("Aman Rathore");
        existingUser.setUser_email("amanrathore@gmail.com");

        when(userRepo.findById(101)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(UserSchema.class))).thenReturn(existingUser);

        UserSchema updatedUser = userServices.updateUser(101, user);

        assertEquals("Himanshu Rathore", updatedUser.getUser_name());
        assertEquals("himanshurathore@gmail.com", updatedUser.getUser_email());
    }

  
}

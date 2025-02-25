
package com.aman.editschema.controller;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aman.editschema.model.User;
import com.aman.editschema.model.UserSchema;
import com.aman.editschema.services.UserServices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;


class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServices userServices;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    

    @Test
    void testAddUser() throws Exception {
    	System.out.println("Add user Controller run ");
        User user = new User();
        user.setUser_id(101);
        user.setUser_name("Aman Rathore");
        user.setUser_email("amanrathore@gmail.com");

        
        // actual value from mock is coming from here
        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User Added Successfully"));

        // verifying the expected and actual value
        verify(userServices, times(1)).saveUser(any(User.class));
    }

    @Test
    void testFetchUser() throws Exception {
     	System.out.println("Fetch user Controller run ");
        UserSchema user = new UserSchema();
        user.setUser_id(101);
        user.setUser_name("Aman Rathore");
        user.setUser_email("amanrathore@gmail.com");

        when(userServices.getUserById(101)).thenReturn(user);

        mockMvc.perform(get("/user/fetch").param("user_id", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(101))
                .andExpect(jsonPath("$.user_name").value("Aman Rathore"))
                .andExpect(jsonPath("$.user_email").value("amanrathore@gmail.com"));
    }

    @Test
    void testFetchAllUsers() throws Exception {
     	System.out.println("Fetch All user Controller run ");
        List<UserSchema> userList = List.of(new UserSchema(), new UserSchema());
        when(userServices.getAllUser()).thenReturn(userList);

        mockMvc.perform(get("/user/fetch/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
     	System.out.println("Update user Controller run ");
        User user = new User();
        user.setUser_name("Himanshu Rathore");
        user.setUser_email("himanshurathore@gmail.com");

        UserSchema updatedUser = new UserSchema();
        updatedUser.setUser_id(101);
        updatedUser.setUser_name("Himanshu Rathore");
        updatedUser.setUser_email("himanshurathore@gmail.com");
  

        when(userServices.updateUser(eq(101), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/user/update/101")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_name").value("Himanshu Rathore"))
                .andExpect(jsonPath("$.user_email").value("himanshurathore@gmail.com"));
    }
}


//Another method

//package com.aman.editschema.controller;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.aman.editschema.model.User;
//import com.aman.editschema.model.UserSchema;
//import com.aman.editschema.services.UserServices;
//
//@ExtendWith(MockitoExtension.class)
//class UserControllerTest {
//
//  @InjectMocks
//  private UserController userController;
//
//  @Mock
//  private UserServices userServices;
//
//
//  @Test
//  void testAddUser() {
//  	
//  	System.out.println("Add user Controller run ");
//      User user = new User();
//      user.setUser_id(101);
//      user.setUser_name("Aman Rathore");
//      user.setUser_email("amanrathore@gmail.com");
//
//      String response = userController.addUser(user);
//      
//      verify(userServices, times(1)).saveUser(user);
//
//      assertEquals("User Added Successfully", response);
//  }
//
//  @Test
//  void testFetchUser() {
//  	
//   	System.out.println("Fetch user Controller run ");
//      UserSchema user = new UserSchema();
//      user.setUser_id(101);
//      user.setUser_name("Aman Rathore");
//      user.setUser_email("amanrathore@gmail.com");
//
//      when(userServices.getUserById(101)).thenReturn(user);
//
//      UserSchema response = userController.fetchUser(101);
//
//      verify(userServices, times(1)).getUserById(101);
//
//      assertNotNull(response);
//      assertEquals(101, response.getUser_id());
//      assertEquals("Aman Rathore", response.getUser_name());
//      assertEquals("amanrathore@gmail.com", response.getUser_email());
//  }
//
//  @Test
//  void testFetchAllUsers() {
//  	
//  	System.out.println("Fetch All user Controller run ");
//      List<UserSchema> userList = List.of(new UserSchema(), new UserSchema());
//
//
//      when(userServices.getAllUser()).thenReturn(userList);
//
//      List<UserSchema> response = userController.fetchAllUsers();
//
//      verify(userServices, times(1)).getAllUser();
//
//      assertNotNull(response);
//      assertEquals(2, response.size());
//  }
//
//  @Test
//  void testUpdateUser() {
// 
//   	System.out.println("Update user Controller run ");
//      User user = new User();
//      user.setUser_name("Himanshu Rathore");
//      user.setUser_email("himanshurathore@gmail.com");
//
//      UserSchema updatedUser = new UserSchema();
//      updatedUser.setUser_id(101);
//      updatedUser.setUser_name("Himanshu Rathore");
//      updatedUser.setUser_email("himanshurathore@gmail.com");
//
//      when(userServices.updateUser(eq(101), any(User.class))).thenReturn(updatedUser);
//
//      UserSchema response = userController.updateUser(101, user);
//
//      verify(userServices, times(1)).updateUser(eq(101), any(User.class));
//
//      assertNotNull(response);
//      assertEquals(101, response.getUser_id());
//      assertEquals("Himanshu Rathore", response.getUser_name());
//      assertEquals("himanshurathore@gmail.com", response.getUser_email());
//  }
//}

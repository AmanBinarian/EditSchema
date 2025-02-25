package com.aman.editschema.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.aman.editschema.model.User;
import com.aman.editschema.model.UserSchema;
import com.aman.editschema.services.UserServices;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	
	@Autowired
    private UserServices userService1;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService1.saveUser(user);
        return "User Added Successfully";
    }

    @GetMapping("/fetch")
    public UserSchema fetchUser(@RequestParam int user_id) {
                        
            UserSchema user = userService1.getUserById(user_id);
            return user;
    }
    
    @GetMapping("/fetch/all")
    public List<UserSchema> fetchAllUsers() {
        return userService1.getAllUser();
    }
    
    @PutMapping("/update/{id}")
    public UserSchema updateUser(@PathVariable int id, @RequestBody User user) {
        return userService1.updateUser(id, user);
    }
    
}

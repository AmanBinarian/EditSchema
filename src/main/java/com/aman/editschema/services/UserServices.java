package com.aman.editschema.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aman.editschema.model.User;
import com.aman.editschema.model.UserSchema;
import com.aman.editschema.repository.UserRepo;

@Service
public class UserServices {
	@Autowired
    private UserRepo repo;
	
    public void saveUser(User user) {
    	UserSchema user1= new UserSchema();
    	user1.setUser_id(user.getUser_id());
    	user1.setUser_name(user.getUser_name());
    	user1.setUser_email(user.getUser_email());
    	repo.save(user1);    }

    public UserSchema getUserById(int id) {
        return repo.findById(id).orElse(null);
    }


    public List<UserSchema> getAllUser() {
    	return repo.findAll();
    }
    
    public UserSchema updateUser(int id, User user) {

        Optional<UserSchema> user2 = repo.findById(id);
        
        if (user2.isPresent()) {
            UserSchema existingUser = user2.get();

            if (user.getUser_name() != null) {
                existingUser.setUser_name(user.getUser_name());
            }

          if (user.getUser_email() != null) {
                existingUser.setUser_email(user.getUser_email());
            }

            return repo.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}

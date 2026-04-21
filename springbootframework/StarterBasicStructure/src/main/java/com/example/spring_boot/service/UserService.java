package com.example.spring_boot.service;


import com.example.spring_boot.exceptions.UserAlreadyPresent;
import com.example.spring_boot.exceptions.UserNotFoundException;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final Logger logger= LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
         this.userRepository=userRepository;
    }

    public User createUser(User user)
    {

        Optional<User> optionalUser= userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyPresent("User with id " + user.getId() + " already exists");
        } else {
            userRepository.save(user);
            System.out.println(user.getEmail());
            return user;
        }
    }

    public User updateUser(User user)
    {
        logger.info("Creating User...INFO");
        logger.debug("Creating User...DEBUG");
        logger.trace("Creating User...TRACE");
        logger.warn("Creating User...WARN");
        logger.error("Creating User...ERROR");
        User exsistingUser= userRepository.findById(user.getId()).orElseThrow(()->
                new UserNotFoundException("User with id "+user.getId() +" does not exists"));

        exsistingUser.setName(user.getName());
        exsistingUser.setEmail(user.getEmail());
        userRepository.save(exsistingUser);
        return user;

    }

    public boolean deleteUser(Integer id) {

      User user= userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id " + id + " does not exsist")
                );

            userRepository.delete(user);
            return true;
    }

    public List<User> getAllUsers() {
       List<User> optionalUsers=userRepository.findAll();
        if(optionalUsers.isEmpty())
            throw new NullPointerException("No users found in the database");
        return optionalUsers;
    }

    public User getUserById(int id) {
        User user=userRepository.findById(id).orElseThrow(
                ()->new NullPointerException("User is not present")
        );
        return user;
    }

    public List<User> searchUsers(String name, String email) {
//       return userRepository.findAll().stream()
//                .filter(u->u.getName().equalsIgnoreCase(name))
//                .filter(u->u.getEmail().equalsIgnoreCase(email))
//                .toList();
        return userRepository.findByNameAndEmail(name,email);
    }
}

package org.tsp.eshopplum.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tsp.eshopplum.config.MyUserDetails;
import org.tsp.eshopplum.entities.*;
import org.tsp.eshopplum.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {return userRepository.findAll();}

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User with id: " + id + " not found" ));
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            try {
                userRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {

            throw new RuntimeException("User with id: " + id + " not found" );
        }
    }

    public User update(Long id, User data) {
        try {
            User user = userRepository.getReferenceById(id);
            updateUser(user, data);
            return userRepository.save(user);
        }
        catch(EntityNotFoundException e) {
            throw new RuntimeException("User with id: " + id + " not found" );
        }
    }

    private void updateUser(User user, User data) {
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPhone(data.getPhone());
    }

    public LoginResponse loginUser(LoginReq loginReq){
        Optional<User> user = userRepository.findByName(loginReq.getName());

        if (user.isPresent()) {
            if (passwordEncoder.matches(loginReq.getPassword(), user.get().getPassword())) {
                return new LoginResponse("Login Success", true);
            }else {
                return new LoginResponse("Login Failed: incorrect password", false);
            }
        } else {
            return new LoginResponse("User "+ loginReq.getName() + " doesn't exists", false);
        }

        /*if (user != null) {
            if (passwordEncoder.matches(loginReq.getPassword(), user.get().getPassword())) {
                return new LoginResponse("Login Success", true);
            }else {
                return new LoginResponse("Login Failed: incorrect password", false);
            }
        } else {
            return new LoginResponse("User "+ loginReq.getUsername() + " doesn't exists", false);
        }*/
    }


}

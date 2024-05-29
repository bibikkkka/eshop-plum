package org.tsp.eshopplum.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.tsp.eshopplum.entities.User;
import org.tsp.eshopplum.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {return userRepository.findAll();}

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User with id: " + id + " not found" ));
    }

    public User save(User user) {
        return userRepository.save(user);
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
}

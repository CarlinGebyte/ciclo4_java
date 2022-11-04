package com.reto4.services;

import com.reto4.Exceptions.BaseCustomException;
import com.reto4.models.Role;
import com.reto4.models.User;
import com.reto4.repositories.RoleRepository;
import com.reto4.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User create(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(e -> {
                    throw new BaseCustomException("El correo ya existe", HttpStatus.BAD_REQUEST.value());
                });

        user.setPassword(SHA256(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(String id, User user) {
        Optional<User> update = userRepository.findById(id);

        if (!update.isPresent()) {
            return new User();
        }

        if (!user.getUsername().isEmpty() | user.getUsername() != null) {
            update.get().setUsername(user.getUsername());
        }
        if (!user.getEmail().isEmpty() | user.getEmail() != null) {
            update.get().setEmail(user.getEmail());
        }
        if (!user.getPassword().isEmpty() | user.getPassword() != null) {
            update.get().setPassword(SHA256(user.getPassword()));
        }
        return userRepository.save(update.get());
    }

    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) userRepository.deleteById(id);
    }

    public User relationRoleUser(String idUser, String idRole) {
        Optional<User> user = userRepository.findById(idUser);
        Optional<Role> role = roleRepository.findById(idRole);

        if (!user.isPresent() || !role.isPresent())
            throw new BaseCustomException("El Usuario/Rol no existe", HttpStatus.BAD_REQUEST.value());

        user.get().setRole(role.get());

        return userRepository.save(user.get());
    }

    public String SHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

package com.reto4.controllers;

import com.reto4.models.User;
import com.reto4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public Optional<User> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/validate")
    public User validate(@RequestBody User user, final HttpServletResponse response) throws IOException {
        Optional<User> reqUser = userService.getByEmail(user.getEmail());
        if (reqUser.isPresent() &&
                reqUser.get().getPassword().equals(userService.SHA256(user.getPassword()))) {
            reqUser.get().setPassword("");
            return reqUser.get();
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") String id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PutMapping("{id}/role/{idRole}")
    public User setUserRole(@PathVariable("id") String idUser, @PathVariable("idRole") String idRole) {
        return userService.relationRoleUser(idUser, idRole);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }

}

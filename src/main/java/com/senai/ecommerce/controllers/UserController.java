package com.senai.ecommerce.controllers;

import com.senai.ecommerce.dtos.ProductDTO;
import com.senai.ecommerce.dtos.UserDTO;
import com.senai.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser (@RequestBody UserDTO userDTO) {
        UserDTO user = userService.create(userDTO);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody UserDTO userDTO) {
        UserDTO user = userService.update(id, userDTO);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
       userService.delete(id);
        return ResponseEntity.noContent().build();
    }



}

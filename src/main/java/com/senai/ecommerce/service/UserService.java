package com.senai.ecommerce.service;

import com.senai.ecommerce.dtos.UserDTO;
import com.senai.ecommerce.entities.User;
import com.senai.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userToDTO(user);
    }

    public UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        userDTO.setPhone(user.getPhone());
        userDTO.setBirthDate(user.getBirthDate());
        return userDTO;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(user -> userToDTO(user)).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        User user = convertUserDTOToUser(userDTO);
        user = userRepository.save(user);
        return userToDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setId(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirthDate());
        userRepository.save(user);
        return userToDTO(user);
    }

    @Transactional
    public void delete(Long id) {userRepository.deleteById(id);}

    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirthDate());
        return user;
    }
}

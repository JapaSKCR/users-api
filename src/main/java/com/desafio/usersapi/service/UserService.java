package com.desafio.usersapi.service;


import com.desafio.usersapi.exception.EmailFoundException;
import com.desafio.usersapi.dto.UserDTO;
import com.desafio.usersapi.entity.User;
import com.desafio.usersapi.repository.UserRepository;

import com.desafio.usersapi.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository UserRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO createUser(UserDTO userDTO){
        User createdUser = userMapper.toModel(userDTO);
        verifyIfEmailExists(createdUser);
        createdUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return userMapper.toDto(UserRepository.save(createdUser));
    }

    public List<UserDTO> listAll(){
        List<User> allUsers = UserRepository.findAll();
        return allUsers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id){
        User user = UserRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return userMapper.toDto(user);
    }

    public UserDTO findByEmail(String email){
        User user = UserRepository.findByEmail(email)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return userMapper.toDto(user);
    }

    public UserDTO update(Long id, UserDTO UserDTO){
        User existingUser = verifyIfUserExists(id);
        User updatedUser = userMapper.toModel(UserDTO);
        updatedUser.setId(id);
        verifyIfEmailExists(updatedUser);
        updatedUser.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        BeanUtils.copyProperties(updatedUser, existingUser, "id");
        return userMapper.toDto(UserRepository.save(existingUser));
    }

    public void delete(Long id){
        verifyIfUserExists(id);
        UserRepository.deleteById(id);
    }

    private User verifyIfUserExists(Long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    private void verifyIfEmailExists(User User){
        Optional<User> foundUser =  UserRepository.findByEmail(User.getEmail());
        if(foundUser.isPresent() && foundUser.get().getId() != User.getId()){
            throw new EmailFoundException(
                    String.format("O e-mail %s já está cadastrado. ", User.getEmail()));
        }
    }




}
package com.desafio.usersapi.controller;


import com.desafio.usersapi.dto.UserDTO;
import com.desafio.usersapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService UserService;

    @ApiOperation(value = "Criar usuário", nickname = "createUser")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO UserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(UserService.createUser(UserDTO));
    }

    @ApiOperation(value = "Listar todos os usuários", nickname = "listUsers")
    @GetMapping
    public ResponseEntity<?> listAll(){
        List<UserDTO> users = UserService.listAll();
        return users.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Buscar usuário por código", nickname = "findUserPorId")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(UserService.findById(id));
    }

    @ApiOperation(value = "Buscar usuário por e-mail", nickname = "findUserByEmail")
    @GetMapping("email/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(UserService.findByEmail(email));
    }

    @ApiOperation(value = "Atualizar usuário", nickname = "updateUserById")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO UserDTO){
        return ResponseEntity.ok(UserService.update(id, UserDTO));
    }

    @ApiOperation(value = "Remover usuário", nickname = "deleteUserPorId")

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        UserService.delete(id);
    }

}

package com.pets.app.security.controllers;



import com.pets.app.dto.MensajeDTO;
import com.pets.app.security.dto.UserDTO;
import com.pets.app.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/read")
    public ResponseEntity<Object> getAllUsers(){

        List<UserDTO> userDTOS = userService.listar();
        return new ResponseEntity<Object>(userDTOS, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/read/single")
    public ResponseEntity<Object> getUserById(@RequestParam long id){

        if(!userService.existsPorId(id)){
            return new ResponseEntity<Object>(new MensajeDTO("User not found"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>(userService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestParam int id, @RequestBody UserDTO userDTO){

        if(!userService.existsPorId(id)){
            return new ResponseEntity<Object>(new MensajeDTO("User not found"), HttpStatus.BAD_REQUEST);
        }

        userService.updateUser(id, userDTO);
        return new ResponseEntity<Object>(new MensajeDTO("User updated successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam int id){

        if(!userService.existsPorId(id)){
            return new ResponseEntity<Object>(new MensajeDTO("User not found"), HttpStatus.BAD_REQUEST);
        }

        userService.deleteUser(id);
        return new ResponseEntity<Object>(new MensajeDTO("User deleted successfully"), HttpStatus.OK);
    }

}

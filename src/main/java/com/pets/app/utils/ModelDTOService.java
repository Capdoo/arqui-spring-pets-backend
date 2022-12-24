package com.pets.app.utils;

import com.pets.app.files.FileUploadService;
import com.pets.app.security.dto.UserDTO;
import com.pets.app.security.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelDTOService {

    @Autowired
    FileUploadService fileUploadService;

    public UserDTO getUserDTOfromModel(UserModel p){

        UserDTO usuarioSingle = new UserDTO();

            usuarioSingle.setId(p.getId());
            usuarioSingle.setLastName(p.getLastName());
            usuarioSingle.setSurName(p.getSurName());
            usuarioSingle.setFirstName(p.getFirstName());

            usuarioSingle.setAddress(p.getAddress());
            usuarioSingle.setDni(p.getDni());
            usuarioSingle.setEmail(p.getEmail());
            usuarioSingle.setUsername(p.getUsername());
            usuarioSingle.setPhone(p.getPhone());
            usuarioSingle.setEncoded(fileUploadService.convertBytesToString(p.getImage()));


        return usuarioSingle;
    }



}

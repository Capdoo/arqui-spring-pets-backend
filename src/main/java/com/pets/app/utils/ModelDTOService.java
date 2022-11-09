package com.pets.app.utils;

import com.pets.app.security.dto.UserDTO;
import com.pets.app.security.models.UserModel;
import org.springframework.stereotype.Service;

@Service
public class ModelDTOService {

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
            usuarioSingle.setUrlLink(p.getLinkImg());

        return usuarioSingle;
    }



}

package com.pets.app.modules.owners;

import com.pets.app.modules.pets.PetModel;
import com.pets.app.modules.shelters.ShelterModel;
import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import com.pets.app.security.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class OwnerServiceTest {

    @Mock
    UserRepository usuarioRepository;
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    UserService userService;
    @InjectMocks
    OwnerService ownerService;

    private OwnerModel ownerModel;
    private UserModel userModel;
    private RoleModel roleModel;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        ownerModel = new OwnerModel();
            ownerModel.setId(new Long(4));
            ownerModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
            ownerModel.setNumberOfPets(new Integer(1));
            ownerModel.setRate(new Integer(2));
            ownerModel.setHistorial_id(new Long(1));
            ownerModel.setUser(new UserModel());
            ownerModel.setPets(Arrays.asList(new PetModel()));

        roleModel = new RoleModel();
            roleModel.setId(new Integer(1));
            roleModel.setRoleName(RoleName.ROLE_USER);

        userModel = new UserModel();
            userModel.setId(new Long(2));
            userModel.setUsername("jimmy.cabello");
            userModel.setDni("08577652");
            userModel.setFirstName("Jimmy");
            userModel.setLastName("Cabello");
            userModel.setSurName("Vargas");
            userModel.setPhone("998776543");
            userModel.setAddress("Los Rosales 625");
            userModel.setEmail("jimmymcguill@islascaiman.gob");
            userModel.setPassword("1234");
            userModel.setLinkImg("urllink");
            userModel.setTokenPassword("ebc");
            userModel.setOwner(ownerModel);
            userModel.setShelter(new ShelterModel());

            Set<RoleModel> role_Set = new HashSet<RoleModel>();
            role_Set.add(roleModel);
            userModel.setRoles(role_Set);

        System.out.println("Antes del test");
        //Test1
        Mockito.when(ownerRepository.findAll()).thenReturn(Arrays.asList(ownerModel));
        //Test2
        Mockito.when(ownerRepository.findByUser(any(UserModel.class))).thenReturn(Optional.of(ownerModel));
        Mockito.when(userService.existsPorId(any(Long.class))).thenReturn(new Boolean(Boolean.TRUE));
        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(userModel));
        //Test3
        Mockito.when(ownerRepository.save(any(OwnerModel.class))).thenReturn(ownerModel);
    }

    @Test
    void listAll() {
        System.out.println("Durante el test");

        List<OwnerDTO> ownerDTOList = ownerService.listAll();
        System.out.println(ownerDTOList);
        Assertions.assertEquals(4, ownerDTOList.get(0).getId());
    }

    @Test
    void existsOwnerByUserId(){
        System.out.println("Durante el test 2");
        boolean expected = new Boolean(Boolean.TRUE);
        boolean res = ownerService.existsOwnerByUserId(new Long(1));
        Assertions.assertEquals(expected, res);
        System.out.println(res);
    }

    @Test
    void saveOwner(){
        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(userModel));
        OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setUser_id(new Long(1));
        System.out.println("Durante el test 3");
        OwnerModel ownerModel1 = ownerService.saveOwner(ownerDTO);
        assertNotNull(ownerModel1);

    }


    @AfterEach
    void tearDown() {
        System.out.println("Despues del test");
    }


}
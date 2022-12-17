package com.pets.app.modules.pets;

import com.pets.app.files.FileUploadService;
import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.details.DetailRepository;
import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.owners.OwnerRepository;
import com.pets.app.modules.shelters.ShelterModel;
import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PetServiceTest {

    @Mock
    PetRepository petRepository;
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    DetailRepository detailRepository;
    @Mock
    FileUploadService fileUploadService;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    PetService petService;

    private OwnerModel ownerModel;
    private RoleModel roleModel;
    private UserModel userModel;
    private DetailModel detailModel;
    private PetModel petModel;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);



        roleModel = new RoleModel();
            roleModel.setId(new Integer(1));
            roleModel.setRoleName(RoleName.ROLE_USER);

        //Set user
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

        petModel = new PetModel();
            petModel.setId(new Long(1));
            petModel.setName("copito");
            petModel.setGender("male");
            petModel.setBirthDate(new Timestamp(System.currentTimeMillis()));
            petModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
            petModel.setColour("white");
            petModel.setCharacteristic("large");
            petModel.setSize("small");
            petModel.setLinkImg("url");
            //petModel.setOwner(new OwnerModel());
            petModel.setDetail(detailModel);
            petModel.setSearchs(null);
            petModel.setAdoptions(null);
            petModel.setShelter(null);

        ownerModel = new OwnerModel();
            ownerModel.setId(new Long(4));
            ownerModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
            ownerModel.setNumberOfPets(new Integer(1));
            ownerModel.setRate(new Integer(2));
            ownerModel.setHistorial_id(new Long(1));
            ownerModel.setPets(Arrays.asList(petModel));
            ownerModel.setUser(userModel);

            petModel.setOwner(ownerModel);

        detailModel = new DetailModel();
            detailModel.setId(new Long(1));
            detailModel.setSpecies("cat");
            detailModel.setBreed("siames");

            petModel.setDetail(detailModel);


        //Test3
        Mockito.when(petRepository.save(any(PetModel.class))).thenReturn(petModel);

    }

    @Test
    void savePet() throws IOException {
        Mockito.when(userRepository.findByUsername("jimmy.cabello")).thenReturn(Optional.of(userModel));
        Mockito.when(ownerRepository.findByUser(userModel)).thenReturn(Optional.of(ownerModel));
        Mockito.when(detailRepository.findBySpeciesAndBreed("cat","albine")).thenReturn(Optional.of(detailModel));

        PetDTO petDTO = new PetDTO();
            petDTO.setId(new Long(2));
            petDTO.setName("copito");
            petDTO.setGender("male");
            petDTO.setBirthDate("12/04/2021");
            petDTO.setRegisterDate("12/12/2022");
            petDTO.setColour("white");
            petDTO.setCharacteristic("large");
            petDTO.setSize("small");
            petDTO.setSpecies("cat");
            petDTO.setBreed("albine");
            petDTO.setIdOwner(new Long(1));
            petDTO.setIdDetail(new Long(1));
            petDTO.setEncoded("");
            petDTO.setUrlLink("");
            petDTO.setEncoded("");

        String username = "jimmy.cabello";
        PetModel petModel = petService.savePet(petDTO, username);
        assertNotNull(petModel);
    }

    @Test
    void listAll(){
        Mockito.when(ownerRepository.findAll()).thenReturn(Arrays.asList(ownerModel));
        Mockito.when(petRepository.findAllById(any(List.class))).thenReturn(Arrays.asList(petModel));
        List<PetDTO> listPets = petService.listAllPets();
        assertNotNull(listPets);
        String expectedGenre = "male";
        assertEquals(expectedGenre, listPets.get(0).getGender());
    }

    @Test
    void getAllByUsername(){

        Mockito.when(userRepository.findByUsername("jimmy.cabello")).thenReturn(Optional.of(userModel));
        Mockito.when(ownerRepository.findByUser(any(UserModel.class))).thenReturn(Optional.of(ownerModel));
        String username = "jimmy.cabello";

        List<PetDTO> listPets = petService.getAllByUsername(username);
        assertNotNull(listPets);

    }

    @AfterEach
    void tearDown() {
        System.out.println("Despues del test");
    }

}
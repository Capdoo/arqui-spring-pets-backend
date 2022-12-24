package com.pets.app.modules.details;

import com.pets.app.dto.StringDTO;
import com.pets.app.modules.pets.PetModel;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DetailServiceTest {

    @Mock
    DetailRepository detailRepository;
    @InjectMocks
    DetailService detailService;

    private DetailModel detailModel;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        detailModel = new DetailModel();
            detailModel.setId(new Long(1));
            detailModel.setSpecies("cat");
            detailModel.setBreed("Siames");
            detailModel.setPets(null);

        Mockito.when(detailRepository.findAll()).thenReturn(Arrays.asList(detailModel));
        Mockito.when(detailRepository.findAllBySpecies(any(String.class))).thenReturn(Arrays.asList(detailModel));
    }

    @Test
    void listAll() {
        DetailDTO detailDTO = new DetailDTO();
            detailDTO.setBreed("Siames");
        List<DetailDTO> list = new ArrayList<DetailDTO>();
            list.add(detailDTO);
        List<DetailDTO> listExpected = detailService.listAll();
        Assertions.assertNotNull(listExpected);
        Assertions.assertEquals(detailDTO.getBreed(), listExpected.get(0).getBreed());
    }

    @Test
    void getAllSpecies(){
        StringDTO stringDTO = new StringDTO();
            stringDTO.setData("cat");
        List<StringDTO> list = new ArrayList<>();
            list.add(stringDTO);
        List<StringDTO> listExpected = detailService.getAllSpecies();
        Assertions.assertNotNull(listExpected);
        Assertions.assertEquals(list.get(0).getData(), listExpected.get(0).getData());
    }

    @Test
    void getBreedsBySpecies(){
        String species = "cat";
        StringDTO stringDTO = new StringDTO();
            stringDTO.setData("Siames");
        List<StringDTO> list = new ArrayList<>();
            list.add(stringDTO);

        List<StringDTO> listExpected = detailService.getBreedsBySpecies(species);
        //if it's null
        Assertions.assertNotNull(listExpected);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Despues del test");
    }
}
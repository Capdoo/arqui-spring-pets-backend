package com.pets.app.modules.pets;

import com.pets.app.modules.adoptions.AdoptionModel;
import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.owners.OwnerRepository;
import com.pets.app.modules.searchs.SearchModel;
import com.pets.app.modules.shelters.ShelterModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PetServiceTest {

    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private PetService petService;


    private PetDTO petDTO;

    private OwnerModel ownerModel;

    private PetModel petModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        petModel = new PetModel();
            petModel.setId(new Long(3));
            petModel.setName("Copito");
            petModel.setGender("male");
            petModel.setBirthDate(new Timestamp(System.currentTimeMillis()));
            petModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
            petModel.setColour("white");
            petModel.setCharacteristic("big eyes");
            petModel.setSize("big");
            petModel.setLinkImg("link...");
            petModel.setOwner(new OwnerModel());
            petModel.setDetail(new DetailModel());
            petModel.setSearchs(new Set<SearchModel>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<SearchModel> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(SearchModel searchModel) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends SearchModel> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }
            });
            petModel.setAdoptions(new Set<AdoptionModel>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<AdoptionModel> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(AdoptionModel adoptionModel) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends AdoptionModel> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }
            });
            petModel.setShelter(new ShelterModel());

        petDTO = new PetDTO();
        petDTO.setId(new Long(2));
        petDTO.setGender("female");
        petDTO.setBirthDate("12/12/12");
        petDTO.setRegisterDate("12/12/12");
        petDTO.setColour("black");
        petDTO.setCharacteristic("big eyes");
        petDTO.setSize("large");
        petDTO.setSpecies("cat");
        petDTO.setBreed("blue russian cat");
        petDTO.setIdOwner(new Long(1));
        petDTO.setIdDetail(new Long(2));
        petDTO.setEncoded("encoded");
        petDTO.setUrlLink("urlink");

//        ownerModel = new OwnerModel();
//        ownerModel.set

    }

    @Test
    void listAllPets() {
        List<Long> longList = new ArrayList<Long>();
        longList.add(1L);
        when(petRepository.findAllById(longList)).thenReturn(Arrays.asList(petModel));
        assertNotNull(petService.listAllPets());
    }

    @Test
    void getAllByUsername() {
    }
}
package com.pets.app.modules.searchs;

import com.pets.app.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/searchs")
public class SearchsController {
	
	
	@Autowired
    SearchService searchService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createSearch(@RequestBody SearchDTO searchDTO){
		
		try {
			searchService.saveSearch(searchDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Search registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<Object> readSearchs(){
		
		try {
			List<SearchDTO> listaBusquedas = searchService.listAll();
			return new ResponseEntity<Object>(listaBusquedas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/read/single")
	public ResponseEntity<Object> readById(@RequestParam long id){

		try {
			SearchDTO busqueda = searchService.getSearchById(id);
			return new ResponseEntity<Object>(busqueda, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/read/pet")
	public ResponseEntity<Object> readByPetId(@RequestParam long id){
		
		try {
			List<SearchDTO> listaBusquedas = searchService.getSearchByPetId(id);
			return new ResponseEntity<Object>(listaBusquedas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}



}

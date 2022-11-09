package com.pets.app.modules.shelters;

import com.pets.app.files.FileUploadService;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import com.pets.app.security.services.UserService;
import com.pets.app.utils.FechaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShelterService {

	@Autowired
    ShelterRepository shelterRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	FileUploadService fileUploadService;

	@Autowired
	UserService userService;

	public void saveShelter(ShelterDTO shelterDTO) throws IOException {
		
		//UsuarioModel usuarioRepresentante = usuarioRepository.findById(refugioDTO.getIdRepresentante()).get();
		UserModel usuarioRepresentante = userRepository.findByDni(shelterDTO.getDniMainPartner()).get();

		
		ShelterModel refugioNuevo = new ShelterModel();
			refugioNuevo.setAddress(shelterDTO.getAddress());
			refugioNuevo.setDistrict(shelterDTO.getDistrict());
			refugioNuevo.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			refugioNuevo.setName(shelterDTO.getName());
			refugioNuevo.setNumberOfPartners(shelterDTO.getNumberOfPartners());
			refugioNuevo.setContactNumber(shelterDTO.getContactNumber());
			refugioNuevo.setUser(usuarioRepresentante);

			try {
				String encoded = fileUploadService.obtenerEncoded(shelterDTO.getEncoded());
				byte[] imagen = fileUploadService.convertStringToBytes(encoded);
				String url = fileUploadService.fileUpload(imagen);
				refugioNuevo.setLinkImg(url);
			}catch (Exception e){
				e.printStackTrace();
			}

		shelterRepository.save(refugioNuevo);
	}
	

	//Obtener todos
	public List<ShelterDTO> listAll(){
		List<ShelterDTO> listaEnviar = new ArrayList<>();
		List<ShelterModel> listaBD = shelterRepository.findAll();

		
		for(ShelterModel p : listaBD) {
			FechaUtil fechaUtil = new FechaUtil();
			ShelterDTO refugioSingle = new ShelterDTO();

				refugioSingle.setId(p.getId());
				refugioSingle.setAddress(p.getAddress());
				refugioSingle.setDistrict(p.getDistrict());

					String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
					refugioSingle.setRegisterDate(fechaRegistro);
				
					//Nuevo
				//refugioSingle.setIdRepresentante(null);
				refugioSingle.setName(p.getName());
				refugioSingle.setNumberOfPartners(p.getNumberOfPartners());
				refugioSingle.setContactNumber(p.getContactNumber());
				refugioSingle.setDniMainPartner(p.getUser().getDni());

				refugioSingle.setUrlLink(p.getLinkImg());
				
			listaEnviar.add(refugioSingle);
			
		}
		return listaEnviar;
	}
	
	//Obtener por dni user
	public List<ShelterDTO> getByDniUser(String dniUser){
		List<ShelterDTO> listSend = new ArrayList<>();
		UserModel usuarioModel = userRepository.findByDni(dniUser).get();
		
		List<ShelterModel> listaDB = shelterRepository.findAllByUser(usuarioModel);
		
		for(ShelterModel p : listaDB) {
			FechaUtil fechaUtil = new FechaUtil();
			ShelterDTO refugioSingle = new ShelterDTO();

				refugioSingle.setId(p.getId());
				refugioSingle.setAddress(p.getAddress());
				refugioSingle.setDistrict(p.getDistrict());

					String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
					refugioSingle.setRegisterDate(fechaRegistro);
				
				//refugioSingle.setIdRepresentante(p.getUsuario().getId());
				refugioSingle.setName(p.getName());
				refugioSingle.setNumberOfPartners(p.getNumberOfPartners());
				refugioSingle.setContactNumber(p.getContactNumber());
				refugioSingle.setDniMainPartner(p.getUser().getDni());

				refugioSingle.setUrlLink(p.getLinkImg());

			listSend.add(refugioSingle);
			
		}
		return listSend;
	}
	
	//Obtener por id
	public ShelterDTO getById(long id){

		ShelterModel p = shelterRepository.findById(id).get();

		FechaUtil fechaUtil = new FechaUtil();
		ShelterDTO refugioSingle = new ShelterDTO();

			refugioSingle.setId(p.getId());
			refugioSingle.setAddress(p.getAddress());
			refugioSingle.setDistrict(p.getDistrict());

				String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
				refugioSingle.setRegisterDate(fechaRegistro);
			
			//refugioSingle.setIdRepresentante(p.getUsuario().getId());
			refugioSingle.setName(p.getName());
			refugioSingle.setNumberOfPartners(p.getNumberOfPartners());
			refugioSingle.setContactNumber(p.getContactNumber());
			refugioSingle.setDniMainPartner(p.getUser().getDni());

			refugioSingle.setUrlLink(p.getLinkImg());

		return refugioSingle;
	}

	//Util
	public boolean existsRefugioByUserId(long idUsuario){
		boolean res = false;

		if(userService.existsPorId(idUsuario)) {
			UserModel usuario = userRepository.findById(idUsuario).get();

			List<ShelterModel> shelterModel = shelterRepository.findAllByUser(usuario);

			if(!shelterModel.isEmpty()) {
				res = true;
			}
		}
		return res;
	}
}

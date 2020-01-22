package dev.hotel.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;

@RestController
@RequestMapping(value = "/client")
public class ClientsController {

	private ClientRepository clientrepository;

	public ClientsController(ClientRepository clientrepository) {
		super();
		this.clientrepository = clientrepository;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/client")
	public List<Client> retourneListeClients() {
		return this.clientrepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, params= "nom")
	public List<Client> retourneNomParticulier(@RequestParam("nom") String nomParticulier) {
		if (this.clientrepository.findAll().isEmpty() == true) {
			return new ArrayList<>();
		} else if (this.clientrepository.findByNom(nomParticulier) == null) {
			return null;
		} else {

			return this.clientrepository.findByNom(nomParticulier);
		}
	}

	@RequestMapping (method = RequestMethod.POST)
	public ResponseEntity<?> postClient(@RequestBody Client postclient) {
		List<Client> listeClientsTrouves = this.clientrepository.findByNomAndPrenoms(postclient.getNom(), postclient.getPrenoms());
		
		if(!listeClientsTrouves.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur type 404 : Le client existe déjà");}
				
		else {	Client nouveauclient = new Client();
		nouveauclient.setNom(postclient.getNom().toUpperCase());
		nouveauclient.setPrenoms(postclient.getPrenoms().toUpperCase());
		this.clientrepository.save(nouveauclient);
		return ResponseEntity.status(HttpStatus.CREATED).body(nouveauclient);	
		 }
		 };
}
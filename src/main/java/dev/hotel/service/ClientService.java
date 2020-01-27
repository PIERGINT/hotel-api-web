package dev.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientrepository;

	public ClientService(ClientRepository clientrepository) {
		super();
		this.clientrepository = clientrepository;
	}

	public List<Client> afficherListeClients() {
		return this.clientrepository.findAll();
	}

	public List<Client> chercherParNom(String nom) {
		if (this.clientrepository.findAll().isEmpty() == true) {
			return new ArrayList<>();
		} else if (this.clientrepository.findByNom(nom) == null) {
			return null;
		} else {

			return this.clientrepository.findByNom(nom);}
		}

	public Optional<Client> chercherParId(UUID clientId) {
		return clientrepository.findById(clientId);
	}
	
	public UUID creerClient(@RequestBody Client clienttape) {
		List<Client> listeClientsTrouves = this.clientrepository.findByNomAndPrenoms(clienttape.getNom(), clienttape.getPrenoms());
		
		if(!listeClientsTrouves.isEmpty()) {
			throw new EntityExistsException();}
				
		else {
		return this.clientrepository.save(clienttape).getUuid();	
		 }
		 };
}
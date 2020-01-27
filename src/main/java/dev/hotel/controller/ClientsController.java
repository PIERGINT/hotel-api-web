package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
import dev.hotel.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientsController {

	private ClientService clientservice;

	public ClientsController(ClientService clientservice) {
		super();
		this.clientservice = clientservice;
	}

	@GetMapping
	public List<Client> afficherListeClients() {
		return this.clientservice.afficherListeClients();
	}

	@GetMapping(params = "nom")
	public List<Client> chercherParNom(@RequestParam("nom") String nomParticulier) {

		return this.clientservice.chercherParNom(nomParticulier);
	}

	@PostMapping
	public UUID creerClient(@RequestBody @Valid Client postclient) {
		return clientservice.creerClient(postclient);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException except) {
		return ResponseEntity.badRequest().body(except.getMessage());
	}

	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> clientexiste(EntityExistsException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur type 404 : Le client existe déjà");
	}
}
package dev.hotel.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationRequest;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;
import dev.hotel.service.ClientService;
import dev.hotel.service.ReservationService;
import dev.hotel.entite.BaseEntite;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {

	private ReservationService reservationservice;
	private ClientService clientservice;
	private ChambreRepository chambrerepository;

	public ReservationController(ReservationService reservationservice, ClientService clientservice,
			ChambreRepository chambrerepository) {
		super();
		this.reservationservice = reservationservice;
		this.clientservice = clientservice;
		this.chambrerepository = chambrerepository;
	}

	@GetMapping
	public List<Reservation> afficherListeReserv() {
		return reservationservice.afficherListeReserv();
	}
	
	@PostMapping
	public ResponseEntity<?> postReservation(@RequestBody @Valid ReservationRequest newResa) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationservice.creerReservation(newResa.getDateDebut(),
				newResa.getDateFin(), newResa.getClientId(), newResa.getChambreId()));

	}
	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> clientinexistant(EntityExistsException exception1) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception1.getMessage());
	}
	
	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> chambreinexistante(EntityExistsException exception2) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception2.getMessage());
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> erreurdate(Exception exception3) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception3.getMessage());
	}
}
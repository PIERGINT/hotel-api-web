package dev.hotel.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationRequest;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository reservationrepository;
	private ClientRepository clientrepository;
	private ChambreRepository chambrerepository;

	public ReservationService(ReservationRepository reservationrepository, ClientRepository clientrepository,
			ChambreRepository chambrerepository) {
		super();
		this.reservationrepository = reservationrepository;
		this.clientrepository = clientrepository;
		this.chambrerepository = chambrerepository;
	}

	public List<Reservation> afficherListeReserv() {
		return this.reservationrepository.findAll();
	}

	public Reservation creerReservation(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambresId) throws Exception {

		Reservation resabody = new Reservation();
		resabody.setDateDebut(dateDebut);
		resabody.setDateFin(dateFin);
		if (dateDebut.isAfter(dateFin) == true) {
			throw new Exception("Erreur type 404 : La date d'arrivée doit être antérieure à la date de départ");
		}
		resabody.setClient(clientrepository.findById(clientId)
				.orElseThrow(() -> new EntityNotFoundException("Erreur type 404 : Client non trouvé")));
		resabody.setChambres(chambresId.stream()
				.map(chambreresId -> chambrerepository.findById(chambreresId)
						.orElseThrow(() -> new EntityNotFoundException("Erreur type 404 : La chambre n'existe pas")))
				.collect(Collectors.toList()));
		
		return reservationrepository.save(resabody);
	}
}

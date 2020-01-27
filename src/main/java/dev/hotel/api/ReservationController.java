package dev.hotel.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationRequest;
import dev.hotel.entite.BaseEntite;

@RestController
public class ReservationController {

	private ReservationRepository reservationrepository;
	private ClientRepository clientrepository;
	private ChambreRepository chambrerepository;

	public ReservationController(ReservationRepository reservationrepository, ClientRepository clientrepository,
			ChambreRepository chambrerepository) {
		super();
		this.reservationrepository = reservationrepository;
		this.clientrepository = clientrepository;
		this.chambrerepository = chambrerepository;
	}

	@RequestMapping(method = RequestMethod.POST, path = "reservation")
	public ResponseEntity<?> postReservation(@RequestBody ReservationRequest newResa) {

		Reservation resabody = new Reservation();
		ResponseEntity<?> reponse = null;
		List<Chambre> chambre = new ArrayList<>();
		if (this.clientrepository.findById(newResa.getClientId()).isPresent()) {

			Client client = this.clientrepository.findById(newResa.getClientId()).get();

			List<UUID> listeuuid = newResa.getChambreId();
			for (UUID c : listeuuid) {
				if (this.chambrerepository.findById(c).isPresent()) {
					Chambre chambrereserv = new Chambre();
					chambrereserv = this.chambrerepository.findById(c).get();
					chambre.add(chambrereserv);
					resabody.setChambres(chambre);
					resabody.setDateDebut(newResa.getDateDebut());
					resabody.setDateFin(newResa.getDateFin());
					resabody.setClient(client);
					reponse = ResponseEntity.status(HttpStatus.CREATED).body(resabody);
					this.reservationrepository.save(resabody);
					
				} else if (!this.chambrerepository.findById(c).isPresent()) {
					reponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Erreur type 404 : la chambre n’existe pas");
				} else if (newResa.getDateDebut().isAfter(newResa.getDateFin()) == true) {
					reponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Erreur type 404 : La date d'arrivée doit être antérieure à la date de départ");
				}
			}

		} else {
			reponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur type 404 : Client non trouvé");

		}
		
		return reponse;

	}
}
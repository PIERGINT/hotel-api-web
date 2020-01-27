package dev.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import dev.hotel.api.ChambreRepository;
import dev.hotel.api.ClientRepository;
import dev.hotel.api.ReservationRepository;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Hotel;
import dev.hotel.entite.ReservationRequest;


@Component
public class Startup {

	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;
	private HotelRepository hotelRepository;

	public Startup(ClientRepository clientRepository, HotelRepository hotelRepository,
			ChambreRepository chambreRepository) {
		super();
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
		this.hotelRepository = hotelRepository;
	}

	Hotel hotel = new Hotel();
	Hotel hotel2 = new Hotel();
	
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		if (this.clientRepository.count() == 0) {
			Client client = new Client();
			client.setNom("PIERRE");
			client.setPrenoms("ANDRE");
			this.clientRepository.save(client);
		}
		if (this.clientRepository.count() == 1) {
			Client client2 = new Client();
			client2.setNom("AMICE");
			client2.setPrenoms("STEPHANIE");
			this.clientRepository.save(client2);
		}
		if (this.clientRepository.count() == 2) {
			Client client3 = new Client();
			client3.setNom("PIERRE");
			client3.setPrenoms("LOUIS");
			this.clientRepository.save(client3);
		}

		if (this.hotelRepository.count() == 0) {
			hotel.setNom("DOUCENUIT");
			hotel.setNombreEtoiles(2);
			this.hotelRepository.save(hotel);
		}
		
		if (this.hotelRepository.count() == 1) {
			hotel2.setNom("LUXOR");
			hotel2.setNombreEtoiles(5);
			this.hotelRepository.save(hotel2);
		}
		
		if (this.chambreRepository.count() == 0 && this.hotelRepository.count() != 0 ) {
			
			Chambre chambre = new Chambre();
			chambre.setNumero("1");
			chambre.setSurfaceEnM2(15.50f);
			chambre.setHotel(hotel);
			this.chambreRepository.save(chambre);
			
		}
		if (this.chambreRepository.count() == 1 && this.hotelRepository.count() != 0 ) {
			Chambre chambre2 = new Chambre();
			chambre2.setNumero("2");
			chambre2.setSurfaceEnM2(20f);
			chambre2.setHotel(hotel);
			this.chambreRepository.save(chambre2);
			
		}
		if (this.chambreRepository.count() == 2 && this.hotelRepository.count() != 0 ) {
			Chambre chambre3 = new Chambre();
			chambre3.setNumero("3");
			chambre3.setSurfaceEnM2(55f);
			chambre3.setHotel(hotel2);
			this.chambreRepository.save(chambre3);
			
		}
		if (this.chambreRepository.count() == 3 && this.hotelRepository.count() != 0 ) {
			Chambre chambre4 = new Chambre();
			chambre4.setNumero("4");
			chambre4.setSurfaceEnM2(40f);
			chambre4.setHotel(hotel2);
			this.chambreRepository.save(chambre4);
			
		}
		
	}
}

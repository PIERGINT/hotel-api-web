package dev.hotel.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;

public class ChambresController {

	private ChambreRepository chambrerepository;

	public ChambresController(ChambreRepository chambrerepository) {
		super();
		this.chambrerepository = chambrerepository;
	}

	@RequestMapping(method = RequestMethod.GET, path = "chambre")
	public List<Chambre> retourneListeChambres() {
		return this.chambrerepository.findAll();
	}
}

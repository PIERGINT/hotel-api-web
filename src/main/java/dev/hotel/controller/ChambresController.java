package dev.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.repository.ChambreRepository;

@RestController
@RequestMapping(value = "/chambre")
public class ChambresController {

	private ChambreRepository chambrerepository;

	public ChambresController(ChambreRepository chambrerepository) {
		super();
		this.chambrerepository = chambrerepository;
	}

	@GetMapping
	public List<Chambre> retourneListeChambres() {
		return this.chambrerepository.findAll();
	}
}

package dev.hotel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sun.xml.bind.v2.model.core.ID;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

	
			List<Client> findByNom(String nom);
			
			List<Client> findByNomAndPrenoms(String nom, String prenoms);
			
			Optional<Client> findById(UUID clientId);
}

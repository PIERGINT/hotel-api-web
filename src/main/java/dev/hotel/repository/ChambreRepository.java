package dev.hotel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.xml.bind.v2.model.core.ID;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;

public interface ChambreRepository extends JpaRepository<Chambre, UUID>{

	//Optional<Chambre> findByUuId(List<UUID> chambreId);

	

	
}

package dev.hotel.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.xml.bind.v2.model.core.ID;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;

public interface ChambreRepository extends JpaRepository<Chambre, ID>{

	List<Chambre> findByNumero(int numero);
}

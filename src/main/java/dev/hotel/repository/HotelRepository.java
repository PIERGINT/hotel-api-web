package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.xml.bind.v2.model.core.ID;

import dev.hotel.entite.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {

	Hotel findByNom (String nom);
}

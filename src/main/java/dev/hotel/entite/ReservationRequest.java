package dev.hotel.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sun.istack.NotNull;


public class ReservationRequest {
	@NotNull
	private LocalDate dateDebut;
	
	@NotNull
	private LocalDate dateFin;
	@NotNull
	private UUID clientId;
	@NotNull
	private List<UUID> chambreId = new ArrayList<>();

	public ReservationRequest(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambreId) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientId = clientId;
		this.chambreId = chambreId;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID client) {
		this.clientId = client;
	}

	public List<UUID> getChambreId() {
		return chambreId;
	}

	public void setChambreId(List<UUID> chambreId) {
		this.chambreId = chambreId;
	}

	

}

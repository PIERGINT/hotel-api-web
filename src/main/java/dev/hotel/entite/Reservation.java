package dev.hotel.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
@Entity
@Table(name= "reservation")
public class Reservation extends BaseEntite {


    private LocalDate dateDebut;
    private LocalDate dateFin;
   
    @ManyToOne
    private Client client;
    
    @ManyToMany
   // @JoinTable(name="reservation",
   // joinColumns= @JoinColumn(name="uuid_res", referencedColumnName="uuid"),
    //	    inverseJoinColumns= @JoinColumn(name="uuid_cha", referencedColumnName="uuid")
    //)
    private List<Chambre> chambres = new ArrayList<>();

    public Reservation() {
    }

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Client client, List<Chambre> chambres) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.chambres = chambres;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

	@Override
	public String toString() {
		return "Reservation [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", client=" + client + ", chambres="
				+ chambres + "]";
	}
    
}

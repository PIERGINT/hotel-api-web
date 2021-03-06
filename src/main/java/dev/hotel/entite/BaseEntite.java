package dev.hotel.entite;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseEntite {

	@Id
	@org.hibernate.annotations.Type(type="uuid-char")
    private UUID uuid;

    public BaseEntite() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

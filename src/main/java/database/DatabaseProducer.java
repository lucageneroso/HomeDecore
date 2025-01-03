package database;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DatabaseProducer {
@Produces
@PersistenceContext(unitName="HomeDecorePU")
private EntityManager em;
}

package util;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DatabaseProducer{
    @Produces
    @PersistenceContext(unitName="HomeDecorePU")
    EntityManager em;
}
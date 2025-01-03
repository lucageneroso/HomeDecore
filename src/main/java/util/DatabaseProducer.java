package util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped // Add this annotation to make it a CDI bean
public class DatabaseProducer{
    @Produces
    @PersistenceContext(unitName="HomeDecorePU")
    private EntityManager em;
}
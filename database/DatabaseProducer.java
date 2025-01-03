public class DatabaseProducer {
@Produces
@PersistenceContext(unitName="HomeDecorePU")
private EntityManager em;
}

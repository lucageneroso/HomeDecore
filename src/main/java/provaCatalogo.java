import jakarta.inject.Inject;
import service.Catalogo;
import jakarta.inject.Inject;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class provaCatalogo {

    @Inject // Inject the Catalogo bean
    private Catalogo catalogo;

    public static void main(String[] args) {
        // Bootstrap the Weld container
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        // Get an instance of provaCatalogo with injected dependencies
        provaCatalogo app = container.select(provaCatalogo.class).get();

        // Call the method and print the result
        System.out.println(app.catalogo.getProducts());

        // Shutdown the Weld container
        weld.shutdown();
    }
}
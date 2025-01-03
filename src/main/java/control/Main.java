package control;

public class Main {
    @Inject
    private static Catalogo ejb;

    public static void main(String[] args) {
        List<Prodotto> tutti=ejb.getProducts();
        for(Prodotto p: tutti){
            System.out.println(p);
        }
    }
}
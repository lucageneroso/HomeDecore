package enumerativeTypes;
/*
public enum Ruolo {
    CLIENTE, ADMIN, FORNITORE, MAGAZZINIERE, GESTOREORDINI
}

 */


public enum Ruolo {
    CLIENTE("CLIENTE"), MAGAZZINIERE("MAGAZZINIERE"), GESTOREORDINI("GESTOREORDINI"), FORNITORE("FORNITORE"), ADMIN("ADMIN");

    private final String valore;

    Ruolo(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }

    public static Ruolo fromString(String valore) {
        for (Ruolo r : Ruolo.values()) {
            if (r.valore.equals(valore)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Valore non valido per Ruolo: " + valore);
    }
}


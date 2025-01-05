package model.User;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING)
//l'annotazione discriminator column serve a discriminare i diversi record di tipo utente nella tabella
@NamedQueries({
        @NamedQuery(name="Utente.TROVA_TUTTI", query="SELECT u FROM Utente u"),
        @NamedQuery(name="Utente.TROVA_PER_ID", query="SELECT u FROM Utente u WHERE u.id = :id "),
        @NamedQuery(name="Utente.TROVA_PER_EMAIL", query="SELECT u FROM Utente u WHERE u.email = :email"),
        @NamedQuery(name="Utente.TROVA_PER_NOME", query="SELECT u FROM Utente u WHERE u.nome = :nome"),
        @NamedQuery(name="Utente.TROVA_PER_COGNOME", query="SELECT u FROM Utente u WHERE u.cognome = :cognome"),
        @NamedQuery(name="Utente.TROVA_PER_RUOLO", query="SELECT u FROM Utente u WHERE u.ruolo = :ruolo"),

})
public abstract class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String ruolo;

    @Id @GeneratedValue
    private Long id;

    public Utente(){}

    public Utente(String nome, String cognome, String email, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRuolo() {return ruolo;}
    public void setRuolo(String ruolo) {this.ruolo = ruolo;}
    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

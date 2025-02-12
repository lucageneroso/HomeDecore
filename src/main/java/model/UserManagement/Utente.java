package model.UserManagement;

import enumerativeTypes.Ruolo;
import jakarta.persistence.*;

import java.io.Serializable;
/*
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING) //l'annotazione discriminator column serve a discriminare i diversi record di tipo utente nella tabella
@NamedQueries({
        @NamedQuery(name="Utente.TROVA_TUTTI", query="SELECT u FROM Utente u"),
        @NamedQuery(name="Utente.TROVA_PER_ID", query="SELECT u FROM Utente u WHERE u.id = :id "),
        @NamedQuery(name="Utente.TROVA_PER_EMAIL", query="SELECT u FROM Utente u WHERE u.email = :email"),
        @NamedQuery(name="Utente.TROVA_PER_NOME", query="SELECT u FROM Utente u WHERE u.nome = :nome"),
        @NamedQuery(name="Utente.TROVA_PER_COGNOME", query="SELECT u FROM Utente u WHERE u.cognome = :cognome"),
        @NamedQuery(name="Utente.TROVA_PER_RUOLO", query="SELECT u FROM Utente u WHERE u.ruolo = :ruolo"),
        @NamedQuery(name="Utente.TROVA_PER_USERNAME", query="SELECT u FROM Utente u WHERE u.username = :username")
})
public class Utente implements Serializable {
    private String nome;
    private String cognome;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    private String username;

    @Id @GeneratedValue
    private Long id;

    public Utente(){}

    public Utente(String nome, String cognome, String email, String username, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.username = username;
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
    public Ruolo getRuolo() {return ruolo;}
    public void setRuolo(Ruolo ruolo) {this.ruolo = ruolo;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
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
*/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "Utente.TROVA_TUTTI", query = "SELECT u FROM Utente u"),
        @NamedQuery(name = "Utente.TROVA_PER_ID", query = "SELECT u FROM Utente u WHERE u.id = :id"),
        @NamedQuery(name = "Utente.TROVA_PER_EMAIL", query = "SELECT u FROM Utente u WHERE u.email = :email"),
        @NamedQuery(name = "Utente.TROVA_PER_NOME", query = "SELECT u FROM Utente u WHERE u.nome = :nome"),
        @NamedQuery(name = "Utente.TROVA_PER_COGNOME", query = "SELECT u FROM Utente u WHERE u.cognome = :cognome"),

})
public class Utente implements Serializable {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    //private String username;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Utente() {}

    public Utente(String nome, String cognome, String email, String password, Ruolo ruolo) {
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

    @Override
    public String toString() {
        return "Utente{" +
                " Id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Ruolo getRuolo() {return ruolo;}
}
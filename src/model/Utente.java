package model;

import java.sql.Date;

public class Utente {
	String email;
	String nome;
	String cognome;
	String IBAN;
	Date dataNascita;
	String pass;
	int IVA;
	int Tipo_account;
	
	public int getTipo_account() {
		return Tipo_account;
	}
	public void setTipo_account(int x) {
		Tipo_account=x;
	}
	
	public void setIBAN(String x) {
		this.IBAN=x;
	}
	public String getIBAN() {
		return this.IBAN;
	}
	
    public int getIVA() {
        return IVA;
    }
    public void setIVA(int x) {
    	IVA=x;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public Date getData() {
		return dataNascita;
	}
	public void setData(Date x) {
		this.dataNascita=x;
	}
	

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}	

}
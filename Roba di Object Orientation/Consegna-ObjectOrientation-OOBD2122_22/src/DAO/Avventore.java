package DAO;

public class Avventore {
	private String codCartaIdentit�;
	private String nome;
	private String cognome;
	private String numeroDiTelefono;
	
	public Avventore(String codCartaIdentit�, String nome, String cognome, String numeroDiTelefono) {
		this.codCartaIdentit� = codCartaIdentit�;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
	}

	public String getCodCartaIdentit�() {
		return codCartaIdentit�;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}
}

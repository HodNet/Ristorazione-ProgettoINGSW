package DAO;

public class Avventore {
	private String codCartaIdentità;
	private String nome;
	private String cognome;
	private String numeroDiTelefono;
	
	public Avventore(String codCartaIdentità, String nome, String cognome, String numeroDiTelefono) {
		this.codCartaIdentità = codCartaIdentità;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
	}

	public String getCodCartaIdentità() {
		return codCartaIdentità;
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

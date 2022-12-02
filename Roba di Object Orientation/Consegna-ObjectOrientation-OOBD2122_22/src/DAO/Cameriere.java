package DAO;

public class Cameriere {
	private String codCartaIdentità;
	private String nome;
	private String cognome;
	private String numeroDiTelefono;
	private String dataDiNascita;
	private String città;
	private String indirizzo;
	private String numeroCivico;
	private String sesso;
	private String ristoranteID;
	
	public Cameriere(String codCartaIdentità, String nome, String cognome, String numeroDiTelefono,
			String dataDiNascita, String città, String indirizzo, String numeroCivico, String sesso,
			String ristoranteID) {
		this.codCartaIdentità = codCartaIdentità;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
		this.dataDiNascita = dataDiNascita;
		this.città = città;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.sesso = sesso;
		this.ristoranteID = ristoranteID;
	}

	public Cameriere(String codCartaIdentità, String nome, String cognome, String numeroDiTelefono,
			String dataDiNascita, String ristoranteID) {
		this.codCartaIdentità = codCartaIdentità;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
		this.dataDiNascita = dataDiNascita;
		this.ristoranteID = ristoranteID;
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

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public String getCittà() {
		return città;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public String getSesso() {
		return sesso;
	}

	public String getRistoranteID() {
		return ristoranteID;
	}
}

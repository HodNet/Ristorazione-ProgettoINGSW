package DAO;

public class Cameriere {
	private String codCartaIdentit�;
	private String nome;
	private String cognome;
	private String numeroDiTelefono;
	private String dataDiNascita;
	private String citt�;
	private String indirizzo;
	private String numeroCivico;
	private String sesso;
	private String ristoranteID;
	
	public Cameriere(String codCartaIdentit�, String nome, String cognome, String numeroDiTelefono,
			String dataDiNascita, String citt�, String indirizzo, String numeroCivico, String sesso,
			String ristoranteID) {
		this.codCartaIdentit� = codCartaIdentit�;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
		this.dataDiNascita = dataDiNascita;
		this.citt� = citt�;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.sesso = sesso;
		this.ristoranteID = ristoranteID;
	}

	public Cameriere(String codCartaIdentit�, String nome, String cognome, String numeroDiTelefono,
			String dataDiNascita, String ristoranteID) {
		this.codCartaIdentit� = codCartaIdentit�;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroDiTelefono = numeroDiTelefono;
		this.dataDiNascita = dataDiNascita;
		this.ristoranteID = ristoranteID;
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

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public String getCitt�() {
		return citt�;
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

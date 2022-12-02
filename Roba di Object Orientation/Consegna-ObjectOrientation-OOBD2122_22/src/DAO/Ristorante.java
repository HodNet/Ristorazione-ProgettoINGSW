package DAO;

public class Ristorante {
	private String ID;
	private String nome;
	private String citt�; 
	private String indirizzo; 
	private String numeroCivico; 
	private byte numeroDiSale; 
	private byte oraApertura = 20;
	private byte oraChiusura = 22;
	private String foto;
	
	public Ristorante(String ID, String nome, String citt�, String indirizzo, String numeroCivico, byte numeroSale) {
		this.ID = ID;
		this.nome = nome;
		this.citt� = citt�;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.numeroDiSale = numeroSale;
		this.foto = nome + ID;
	}
	
	public Ristorante(String ID, String nome, String citt�, String indirizzo, String numeroCivico, byte numeroSale, String foto) {
		this.ID = ID;
		this.nome = nome;
		this.citt� = citt�;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.numeroDiSale = numeroSale;
		this.foto = foto;
	}
	
	public String getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCitt�() {
		return citt�;
	}

	public void setCitt�(String citt�) {
		this.citt� = citt�;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numero_civico) {
		this.numeroCivico = numero_civico;
	}
	
	public byte getNumeroDiSale() {
		return numeroDiSale;
	}

	public byte getOraapertura() {
		return oraApertura;
	}

	public byte getOrachiusura() {
		return oraChiusura;
	}
	
	public String getFoto() {
		return foto;
	}
}

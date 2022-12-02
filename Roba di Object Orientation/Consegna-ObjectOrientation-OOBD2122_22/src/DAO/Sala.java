package DAO;

public class Sala {
	private String ID;
	private int numeroDiTavoli;
	private String ristoranteID;
	
	public Sala(String ID, int numeroDiTavoli, String ristoranteID) {
		this.ID = ID;
		this.numeroDiTavoli = numeroDiTavoli;
		this.ristoranteID = ristoranteID;
	}

	public String getID() {
		return ID;
	}
	
	public int getNumeroDiTavoli() {
		return numeroDiTavoli;
	}
	
	public String getRistoranteID() {
		return ristoranteID;
	}
}

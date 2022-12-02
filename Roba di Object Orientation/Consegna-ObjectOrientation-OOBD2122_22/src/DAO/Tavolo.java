package DAO;

public class Tavolo {
	private String tavoloID;
	private int numeroMassimoDiAvventori;
	private String salaID;
	
	public Tavolo(String tavoloID, int numeroMassimoDiAvventori, String salaID) {
		super();
		this.tavoloID = tavoloID;
		this.numeroMassimoDiAvventori = numeroMassimoDiAvventori;
		this.salaID = salaID;
	}

	public String getID() {
		return tavoloID;
	}

	public int getNumeroMassimoDiAvventori() {
		return numeroMassimoDiAvventori;
	}

	public String getSalaID() {
		return salaID;
	}
}

package DAO;

public class Servizio {
	private String codCameriere;
	private String dataDiArrivo;
	private String tavoloID;
	
	public Servizio(String codCameriere, String dataDiArrivo, String tavoloID) {
		this.codCameriere = codCameriere;
		this.dataDiArrivo = dataDiArrivo;
		this.tavoloID = tavoloID;
	}

	public String getCodCameriere() {
		return codCameriere;
	}

	public String getDataDiArrivo() {
		return dataDiArrivo;
	}

	public String getTavoloID() {
		return tavoloID;
	}
}

package DAO;

public class Clientela {
	private String codCartaIdentit�;
	private String dataDiArrivo;
	private String tavoloID;
	
	public Clientela(String codCartaIdentit�, String dataDiArrivo, String tavoloID) {
		this.codCartaIdentit� = codCartaIdentit�;
		this.dataDiArrivo = dataDiArrivo;
		this.tavoloID = tavoloID;
	}

	public String getCodCartaIdentit�() {
		return codCartaIdentit�;
	}

	public String getDataDiArrivo() {
		return dataDiArrivo;
	}

	public String getTavoloID() {
		return tavoloID;
	}
}

package DAO;

public class Clientela {
	private String codCartaIdentità;
	private String dataDiArrivo;
	private String tavoloID;
	
	public Clientela(String codCartaIdentità, String dataDiArrivo, String tavoloID) {
		this.codCartaIdentità = codCartaIdentità;
		this.dataDiArrivo = dataDiArrivo;
		this.tavoloID = tavoloID;
	}

	public String getCodCartaIdentità() {
		return codCartaIdentità;
	}

	public String getDataDiArrivo() {
		return dataDiArrivo;
	}

	public String getTavoloID() {
		return tavoloID;
	}
}

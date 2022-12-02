package GUI;

public class InformazioniScorretteException extends Exception {
	private String message = "La casella di testo non può essere vuota. Inserisci qualcosa e riprova";

	public InformazioniScorretteException() {}
			
	public InformazioniScorretteException(String errorMessage) {
		message = errorMessage;
	}
			
	public String getMessage() {
		return message;
	}
}

package controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DAO.Avventore;
import DAO.AvventoreDAO;
import DAO.Cameriere;
import DAO.CameriereDAO;
import DAO.Clientela;
import DAO.ClientelaDAO;
import DAO.Ristorante;
import DAO.RistoranteDAO;
import DAO.Sala;
import DAO.SalaDAO;
import DAO.Servizio;
import DAO.ServizioDAO;
import DAO.Tavolo;
import DAO.TavoloDAO;
import GUI.ClientelaFrame;
import GUI.DBLogin;
import GUI.ErrorMessage;
import GUI.Home;
import GUI.InformazioniScorretteException;
import GUI.NuovaTavolata;
import GUI.RistoranteFrame;
import GUI.SuccessMessage;
import database.DBConnector;

public class Controller {
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int screenWidth = (int) screenSize.getWidth();
	public static final int screenHeight = (int) screenSize.getHeight();
	
	public static final String queryStatisticheAvventoriGiornalieri = 
			  "SELECT codR, EXTRACT(DAY FROM dataDiArrivo) AS giorno, EXTRACT(MONTH FROM dataDiArrivo) AS mese, EXTRACT(YEAR FROM dataDiArrivo) AS anno,  dataDiArrivo, SUM(n°avventori) AS avventori_tot "
			+ "FROM Ristorante NATURAL JOIN Sala NATURAL JOIN Tavolo NATURAL JOIN Tavolata "
			+ "GROUP BY codR, dataDiArrivo ";
	public static final String queryStatisticheAvventoriMensili = 
			  "SELECT codR, EXTRACT(YEAR FROM dataDiArrivo) AS anno, EXTRACT(MONTH FROM dataDiArrivo) AS mese, SUM(n°avventori) AS avventori_tot "
			+ "FROM Ristorante NATURAL JOIN Sala NATURAL JOIN Tavolo NATURAL JOIN Tavolata "
			+ "GROUP BY codR, EXTRACT(YEAR FROM dataDiArrivo), EXTRACT(MONTH FROM dataDiArrivo) ";
	public static final String queryStatisticheAvventoriAnnuali = 
			  "SELECT codR, EXTRACT(YEAR FROM dataDiArrivo) AS anno, SUM(n°avventori) AS avventori_tot "
			+ "FROM Ristorante NATURAL JOIN Sala NATURAL JOIN Tavolo NATURAL JOIN Tavolata "
			+ "GROUP BY codR, EXTRACT(YEAR FROM dataDiArrivo) ";
	public static ArrayList<String> dayHistogramBins;
	public static ArrayList<Integer> dayHistogramFrequencies;
	public static ArrayList<String> monthHistogramBins;
	public static ArrayList<Integer> monthHistogramFrequencies;
	public static ArrayList<String> yearHistogramBins;
	public static ArrayList<Integer> yearHistogramFrequencies;
	
	
	private static DBLogin DBlogin;
	public static Home home;
	public static RistoranteFrame ristoranteFrame;
	public static ClientelaFrame clientelaFrame;
	public static NuovaTavolata nuovaTavolata;
	
	public static RistoranteDAO ristoranteDAO;
	public static SalaDAO salaDAO;
	public static AvventoreDAO avventoreDAO;
	public static CameriereDAO cameriereDAO;
	public static ClientelaDAO clientelaDAO;
	public static ServizioDAO servizioDAO;
	public static TavoloDAO tavoloDAO;
	
	public static void main(String[] args) {
		DBlogin = new DBLogin();
		DBlogin.setVisible(true);
	}
	
	/*
	 * General Purpose Functions:
	 * 
	 */
	public static void scaleImage(JLabel label, String file_name) {
		ImageIcon icon = new ImageIcon(Controller.class.getResource("/images/" + file_name));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		label.setIcon(scaledIcon);
	}
	
	public static void scaleImage(JButton label, String file_name) {
		ImageIcon icon = new ImageIcon(Controller.class.getResource("/images/" + file_name));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(label.getWidth()-10, label.getHeight()-10, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		label.setIcon(scaledIcon);
	}
	
	/*
	 * DBLogin functions:
	 * 
	 */
	public static void saveAllIntoFile() throws IOException {
		BufferedWriter DBinfo = new BufferedWriter( new FileWriter(DBLogin.DBinfoFilePath) );
		DBinfo.write(DBlogin.getUrl() + "\n");
		DBinfo.write(DBlogin.getUsername() + "\n");
		DBinfo.write(DBlogin.getPassword() + "\n");
		DBinfo.close();
	}
	
	public static String getUrlFromFile() {
		try {
			BufferedReader DBinfo = new BufferedReader( new FileReader(DBLogin.DBinfoFilePath) );
			String ret = DBinfo.readLine();
			DBinfo.close();
			return ret;
		} catch (IOException e) {
			return "jdbc:postgresql://localhost:5432/<NOME_DATABASE>";
		}
	}
	
	public static String getUsernameFromFile() {
		try {
			BufferedReader DBinfo = new BufferedReader( new FileReader(DBLogin.DBinfoFilePath) );
			String ret = DBinfo.readLine();
			ret = DBinfo.readLine();
			DBinfo.close();
			return ret;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String getPasswordFromFile() {
		try {
			BufferedReader DBinfo = new BufferedReader( new FileReader(DBLogin.DBinfoFilePath) );
			String ret = DBinfo.readLine();
			ret = DBinfo.readLine();
			ret = DBinfo.readLine();
			DBinfo.close();
			return ret;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void checkDataBase() {
		try {
			DBConnector.getIstance(DBlogin.getUrl(), DBlogin.getUsername(), DBlogin.getPassword());
			if(DBlogin.isRicordaPasswordSelected())
				saveAllIntoFile();
			try {
				ristoranteDAO = new RistoranteDAO(DBConnector.getConnection());
				salaDAO = new SalaDAO(DBConnector.getConnection());
				avventoreDAO = new AvventoreDAO(DBConnector.getConnection());
				cameriereDAO = new CameriereDAO(DBConnector.getConnection());
				clientelaDAO = new ClientelaDAO(DBConnector.getConnection());
				servizioDAO = new ServizioDAO(DBConnector.getConnection());
				tavoloDAO = new TavoloDAO(DBConnector.getConnection());
			} catch(SQLException exc) {
				ErrorMessage error = new ErrorMessage(DBlogin, "Errore nel cercare di estrarre i dati dal database");
				error.setVisible(true);
				exc.printStackTrace();
			}
			home = new Home();
			home.setVisible(true);
			DBlogin.setVisible(false);
		} catch(ClassNotFoundException exc) {
			ErrorMessage error = new ErrorMessage(DBlogin, "Error in connecting to PostgreSQL server");
			error.setVisible(true);
			exc.printStackTrace();
		} catch(SQLException exc) {
			ErrorMessage error = new ErrorMessage(DBlogin, "Error in connecting to the PostgreSQL Database");
			error.setVisible(true);
			exc.printStackTrace();
		} catch(IOException exc) {
			ErrorMessage error = new ErrorMessage(DBlogin, "Error in saving the password");
			error.setVisible(true);
			exc.printStackTrace();
		}
	}
	
	/*
	 * Home functions:
	 * 
	 */
	public static void goToRistoranteFrame(Ristorante ristoranteScelto) {
		ristoranteFrame = new RistoranteFrame(ristoranteScelto);
		ristoranteFrame.setVisible(true);
		home.setVisible(false);
	}
	
	/*
	 * RistoranteFrame functions:
	 * 
	 */
	public static void backToHome() {
		home.setVisible(true);
		if(nuovaTavolata!=null) backToClientelaFrame();
		if(clientelaFrame!=null) backToRistoranteFrame();
		ristoranteFrame.setVisible(false);
		ristoranteFrame = null;
	}
	
	public static void goToClientelaFrame(Ristorante ristoranteScelto) {
		clientelaFrame = new ClientelaFrame(ristoranteScelto);
		clientelaFrame.setVisible(true);
		ristoranteFrame.setButtonsEnabled(false);
	}
	
	public static String getInfoOf(Ristorante ristorante) {
		String indirizzo = ristorante.getIndirizzo() + " " + ristorante.getNumeroCivico() + ", " + ristorante.getCittà() + "\n";
		String orario = "Aperto dalle " + String.valueOf(ristorante.getOraapertura()) + " alle " + String.valueOf(ristorante.getOrachiusura()) + "\n";
		String sale = "\n" + String.valueOf(ristorante.getNumeroDiSale()) + " Sal" + (ristorante.getNumeroDiSale()==1 ? "a" : "e") + "\n";
		String info = indirizzo + orario + sale;
		
		for(Sala sala : salaDAO.getAllOf(ristorante)) {
			info = info + "Sala " + sala.getID() + ": " + sala.getNumeroDiTavoli() + " tavoli\n";
		}
		
		info = info + "\n\nCamerieri:\n";
		
		for(Cameriere cameriere : cameriereDAO.getCamerieriOf(ristorante)) {
			info = info + "\n" + cameriere.getNome() + " " + cameriere.getCognome() + ", " + cameriere.getNumeroDiTelefono();
			if(cameriere.getCittà()!=null && cameriere.getIndirizzo()!=null)
				info = info + "\nresidente a " + cameriere.getCittà() + ",\n" + cameriere.getIndirizzo() + " " + cameriere.getNumeroCivico();
			info = info + "\nnato il " + cameriere.getDataDiNascita();
			info = info + "\nCarta d'identità: " + cameriere.getCodCartaIdentità() + "\n";
		}
		
		return info;
	}
	
	public static void calculateHistograms(Ristorante ristorante) {
		dayHistogramBins = new ArrayList<String>();
		dayHistogramFrequencies = new ArrayList<Integer>();
		monthHistogramBins = new ArrayList<String>();
		monthHistogramFrequencies = new ArrayList<Integer>();
		yearHistogramBins = new ArrayList<String>();
		yearHistogramFrequencies = new ArrayList<Integer>();
		
		String dayQuery = queryStatisticheAvventoriGiornalieri + " HAVING codR=" + String.valueOf(ristorante.getID()) + " ORDER BY dataDiArrivo ";
		String monthQuery = queryStatisticheAvventoriMensili + " HAVING codR=" + String.valueOf(ristorante.getID()) + " ORDER BY EXTRACT(YEAR FROM dataDiArrivo), EXTRACT(MONTH FROM dataDiArrivo) ";
		String yearQuery = queryStatisticheAvventoriAnnuali + " HAVING codR=" + String.valueOf(ristorante.getID()) + " ORDER BY EXTRACT(YEAR FROM dataDiArrivo) ";
		
		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet table = statement.executeQuery(dayQuery);
			while(table.next()) {
				dayHistogramBins.add(table.getString("giorno") + "/" + table.getString("mese") + "/" + table.getString("anno"));
				dayHistogramFrequencies.add(table.getInt("avventori_tot"));
			}
		} catch(SQLException exc) {
			ErrorMessage error = new ErrorMessage(ristoranteFrame, "Errore nel caricare l'istogramma degli avventori giornalieri");
			error.setVisible(true);
			exc.printStackTrace();
		}
		
		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet table = statement.executeQuery(monthQuery);
			while(table.next()) {
				monthHistogramBins.add(table.getString("mese") + "/" + table.getString("anno"));
				monthHistogramFrequencies.add(table.getInt("avventori_tot"));
			}
		} catch(SQLException exc) {
			ErrorMessage error = new ErrorMessage(ristoranteFrame, "Errore nel caricare l'istogramma degli avventori mensili");
			error.setVisible(true);
			exc.printStackTrace();
		}
		
		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet table = statement.executeQuery(yearQuery);
			while(table.next()) {
				yearHistogramBins.add(table.getString("anno"));
				yearHistogramFrequencies.add(table.getInt("avventori_tot"));
			}
		} catch(SQLException exc) {
			ErrorMessage error = new ErrorMessage(ristoranteFrame, "Errore nel caricare l'istogramma degli avventori annuali");
			error.setVisible(true);
			exc.printStackTrace();
		}
	}
	
	/*
	 * ClientelaFrame functions:
	 * 
	 */
	public static String getInfoOf(Clientela cliente) {
		Avventore avventore = avventoreDAO.get(cliente.getCodCartaIdentità());
		if(avventore == null)
			return "";
		String generalità = avventore.getNome() + " " + avventore.getCognome() + ", " + avventore.getNumeroDiTelefono() + "\n";
		String codCartaIdentità = "codice della Carta d'Identità: " + avventore.getCodCartaIdentità();
		return generalità + codCartaIdentità;
	}
	
	public static String getInfoOfCameriereOf(String data, String tavoloID) {
		Servizio servizio = servizioDAO.get(data, tavoloID);
		if(servizio == null)
			return "";
		Cameriere cameriere = cameriereDAO.get(servizio.getCodCameriere());
		if(cameriere == null)
			return "";
		return "Servito dal cameriere " + cameriere.getNome() + " " + cameriere.getCognome() + ", " + cameriere.getNumeroDiTelefono() + "\n" +
			   "nato il " + cameriere.getDataDiNascita() + (cameriere.getCittà()==null ? "" : " a " + cameriere.getCittà()) + "\n" +
			   "codice della Carta d'Identità: " + cameriere.getCodCartaIdentità();
	}
	
	public static void backToRistoranteFrame() {
		ristoranteFrame.setButtonsEnabled(true);
		if(nuovaTavolata!=null) backToClientelaFrame();
		clientelaFrame.setVisible(false);
		clientelaFrame = null;
	}
	
	public static void goToNuovaTavolata(Ristorante ristoranteScelto) {
		nuovaTavolata = new NuovaTavolata(ristoranteScelto);
		nuovaTavolata.setVisible(true);
		clientelaFrame.setButtonsEnabled(false);
	}
	
	/*
	 * NuovaTavolata functions:
	 * 
	 */
	public static void backToClientelaFrame() {
		clientelaFrame.setButtonsEnabled(true);
		nuovaTavolata.setVisible(false);
		nuovaTavolata = null;
	}
	
	public static String[] getTavoliOf(Ristorante ristorante) {
		LinkedList<String> tavoliSelected = new LinkedList<String>();
		try {
			for(Tavolo tavolo : tavoloDAO.getTavoliOf(ristorante)) {
				tavoliSelected.add(tavolo.getID());
			}
		} catch (SQLException exc) {
			ErrorMessage error = new ErrorMessage(nuovaTavolata, "Errore nell'ottenere i tavoli del ristorante dal Database");
			error.setVisible(true);
			exc.printStackTrace();
		}
		return tavoliSelected.toArray(new String[0]);
	}
	
	public static String[] getInfoCamerieriOf(Ristorante ristorante) {
		LinkedList<String> camerieriSelected = new LinkedList<String>();
		for(Cameriere cameriere : cameriereDAO.getCamerieriOf(ristorante)) {
			camerieriSelected.add(cameriere.getCodCartaIdentità() + " (" + cameriere.getNome() + " " + cameriere.getCognome() + ")");
		}
		return camerieriSelected.toArray(new String[0]);
	}
	
	public static void inserisciTavolata(String tavoloID, String data, Cameriere cameriere, LinkedList<Avventore> avventori) throws InformazioniScorretteException {
		checkInformazioni(tavoloID, data, cameriere, avventori);
		try {
			avventoreDAO.insert(avventori);
			clientelaDAO.insertTavolata(avventori, data, tavoloID);
			servizioDAO.insert(new Servizio(cameriere.getCodCartaIdentità(), data, tavoloID));
			backToClientelaFrame();
			clientelaFrame.refresh();
			ristoranteFrame.addTavolataToHistograms(data, avventori);
			SuccessMessage success = new SuccessMessage(clientelaFrame, "Tavolata inserita con successo all'interno del database");
			success.setVisible(true);
			
		} catch (SQLException exc) {
			ErrorMessage error = new ErrorMessage(nuovaTavolata, "Dati inseriti scorretti. " + exc.getMessage());
			error.setVisible(true);
			exc.printStackTrace();
		}
	}
	
	private static void checkInformazioni(String tavoloID, String data, Cameriere cameriere, LinkedList<Avventore> avventori) throws InformazioniScorretteException {
		if(tavoloID==null || tavoloID.isBlank())
			throw new InformazioniScorretteException("Selezionare un tavolo");
		if(data==null || data.isBlank())
			throw new InformazioniScorretteException("Inserire una data");
		if(cameriere==null)
			throw new InformazioniScorretteException("Selezionare un cameriere");
		if(avventori==null || avventori.isEmpty())
			throw new InformazioniScorretteException("Inserire almeno un avventore");
		
		try {
			Date.valueOf(data);
		} catch(IllegalArgumentException exc) {
			throw new InformazioniScorretteException("Data inserita scorretta");
		}
	}
}

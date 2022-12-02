package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import DAO.Avventore;
import controller.Controller;

public class ListaAvventoriPanel extends JPanel {
	
	private NuovaTavolata nuovaTavolata;
	private LinkedList<Avventore> avventori;
	private int scrollHeight = 10;
	
	public ListaAvventoriPanel(NuovaTavolata nuovaTavolata) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(null);
		avventori = new LinkedList<Avventore>();
		this.nuovaTavolata = nuovaTavolata;
	}
	
	public void addAvventore(String codCartaIdentità, String nome, String cognome, String numeroDiTelefono) throws InformazioniScorretteException {
		if(codCartaIdentità.length()!=9 && codCartaIdentità.length()!=10)
			throw new InformazioniScorretteException("Il codice della carta d'identità deve contenere 9 o 10 caratteri");
		if(nome.isBlank())
			throw new InformazioniScorretteException("Inserire il nome");
		if(cognome.isBlank())
			throw new InformazioniScorretteException("Inserire il cognome");
		if(numeroDiTelefono.isBlank())
			throw new InformazioniScorretteException("Inserire il numero di telefono");
		
		Avventore avventore = new Avventore(codCartaIdentità, nome, cognome, numeroDiTelefono);
		avventori.add(avventore);
		
		String infoAvventore = nome + " " + cognome + ", " + numeroDiTelefono + "\ncodice della Carta d'Identità: " + codCartaIdentità;
		JTextArea avventoreTextArea = new JTextArea();
		avventoreTextArea.setWrapStyleWord(true);
		avventoreTextArea.setLineWrap(true);
		avventoreTextArea.setEditable(false);
		avventoreTextArea.setBounds(10, scrollHeight, 366, 35);
		avventoreTextArea.setText(infoAvventore);
		avventoreTextArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(avventoreTextArea);
		
		JLabel trashIcon = new JLabel("");
		trashIcon.setBounds(376, scrollHeight, 15, 15);
		Controller.scaleImage(trashIcon, "trash icon.png");
		
		trashIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				trashIcon.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				trashIcon.setBorder(null);
				LinkedList<Avventore> nuoviAvventori = new LinkedList<Avventore>(avventori);
				nuoviAvventori.remove(avventore);
				clear();
				for(Avventore a : nuoviAvventori) {
					try {
						addAvventore(a.getCodCartaIdentità(), a.getNome(), a.getCognome(), a.getNumeroDiTelefono());
					} catch (InformazioniScorretteException e1) {}
				}
				
				nuovaTavolata.getAggiungiButton().setEnabled(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				trashIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				trashIcon.setBorder(null);
			}
		});
		add(trashIcon);
		
		scrollHeight+=40;
	}
	
	public LinkedList<Avventore> getAll() {
		return avventori;
	}
	
	public int getNumeroAvventori() {
		return avventori.size();
	}
	
	public void clear() {
		avventori.clear();
		scrollHeight=10;
		removeAll();
		repaint();
	}
}

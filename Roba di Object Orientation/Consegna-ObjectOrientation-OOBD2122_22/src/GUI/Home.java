package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.FontAttribute;

import DAO.Ristorante;
import controller.Controller;

public class Home extends JFrame {
	
	private static final int width = 700;
	private static final int height = 425;
	private static final int x = Controller.screenWidth/2 - width/2;
	private static final int y = Controller.screenHeight/2 - height/2;
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();

	public Home() {
		setTitle("Tracciamento Covid-19 per ristoranti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel titolo = new JLabel("Sistema di tracciamento COVID-19 per ristoranti");
		titolo.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		int scrollHeight = 0;
		for (Ristorante ristorante : Controller.ristoranteDAO.getAll()) {
			JLabel fotoRistorante = new JLabel("");
			fotoRistorante.setBounds(10, 10+scrollHeight, 120, 100);
			Controller.scaleImage(fotoRistorante, ristorante.getFoto());
			panel.add(fotoRistorante);
			
			JLabel nomeRistorante = new JLabel(ristorante.getNome());
			nomeRistorante.setBounds(140, 10+scrollHeight, 300, 35);
			nomeRistorante.setFont(new Font("Tahoma", Font.BOLD, 18));
			panel.add(nomeRistorante);
			
			JLabel indirizzoRistorante = new JLabel(ristorante.getIndirizzo() + " " + ristorante.getNumeroCivico() + ", " + ristorante.getCittà());
			indirizzoRistorante.setBounds(140, 30+scrollHeight, 300, 25);
			indirizzoRistorante.setFont(new Font("Tahoma", Font.PLAIN, 13));
			panel.add(indirizzoRistorante);
			
			JLabel orarioRistorante = new JLabel("Aperto dalle " + String.valueOf(ristorante.getOraapertura()) + " alle " + String.valueOf(ristorante.getOrachiusura()));
			orarioRistorante.setBounds(140, 60+scrollHeight, 200, 25);
			orarioRistorante.setFont(new Font("Tahoma", Font.PLAIN, 13));
			panel.add(orarioRistorante);
			
			JLabel numeroSaleRistorante = new JLabel(String.valueOf(ristorante.getNumeroDiSale()) + " Sal" + (ristorante.getNumeroDiSale()==1 ? "a" : "e"));
			numeroSaleRistorante.setBounds(140, 75+scrollHeight, 200, 25);
			numeroSaleRistorante.setFont(new Font("Tahoma", Font.PLAIN, 13));
			panel.add(numeroSaleRistorante);
			
			JLabel selectionRectangle = new JLabel();
			selectionRectangle.setBounds(0, scrollHeight, 5000, 120);
			selectionRectangle.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Controller.scaleImage(selectionRectangle, "rectangle selected.jpg");
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					Controller.goToRistoranteFrame(ristorante);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Controller.scaleImage(selectionRectangle, "selection rectangle.png");
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					selectionRectangle.setIcon(null);
				}
			});
			panel.add(selectionRectangle);
			
			scrollHeight+=110;
		}
		
		setScrollHeight(scrollHeight);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(titolo, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, 0, 644, Short.MAX_VALUE+10))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(titolo)
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE+10))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private void setScrollHeight(int height) {
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 625, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, height, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
	}
}

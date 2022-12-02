package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;

public class SuccessMessage extends JDialog {

	private static final int width = 360;
	private static final int height = 150;
	
	private final JPanel contentPanel = new JPanel();
	private JFrame frame;

	public SuccessMessage(JFrame frame, String message) {
		setBounds(frame.getX() + frame.getWidth()/2 - width/2, frame.getY() + frame.getHeight()/2 - height/2, width, height);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		
		frame.setEnabled(false);
		this.frame = frame;
		
		JLabel successImage = new JLabel("New label");
		successImage.setBounds(10, 11, 45, 45);
		Controller.scaleImage(successImage, "success.png");
		contentPanel.add(successImage);
		
		JTextArea successMessage = new JTextArea();
		successMessage.setWrapStyleWord(true);
		successMessage.setLineWrap(true);
		successMessage.setEditable(false);
		successMessage.setText(message);
		successMessage.setBackground(new Color(240, 240, 240));
		successMessage.setBounds(65, 11, 269, 45);
		
		JScrollPane scrollableSuccessMessage = new JScrollPane(successMessage);
		scrollableSuccessMessage.setBounds(65, 11, 269, 45);
		scrollableSuccessMessage.setBorder(null);
		scrollableSuccessMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollableSuccessMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(scrollableSuccessMessage);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void close() {
		frame.setEnabled(true);
		setVisible(false);
	}
}

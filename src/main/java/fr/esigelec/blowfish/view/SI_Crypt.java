package fr.esigelec.blowfish.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SI_Crypt {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SI_Crypt window = new SI_Crypt();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SI_Crypt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("D/Crypt");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 316, 250);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		
		JButton decryptBtn = new JButton("Decryption");
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecryptionApp window = new DecryptionApp();
				window.frameDecryption.setVisible(true);
				frame.dispose();
			}
		});
		decryptBtn.setBackground(new Color(255, 200, 0));
		decryptBtn.setBounds(78, 118, 147, 34);
		frame.getContentPane().add(decryptBtn);
		
		JButton encryptBtn = new JButton("Encryption");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EncryptionApp window = new EncryptionApp();
				window.frameEncryption.setVisible(true);
				frame.dispose();
			}
		});
		encryptBtn.setBackground(Color.ORANGE);
		encryptBtn.setBounds(78, 65, 147, 34);
		frame.getContentPane().add(encryptBtn);
		
		JLabel lblNewLabel = new JLabel("Welcome to D/Crypt's Software");
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(34, 11, 256, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblByMorganDara = new JLabel("By Morgan DARA and Lothaire KOUHEVI");
		lblByMorganDara.setVerticalAlignment(SwingConstants.BOTTOM);
		lblByMorganDara.setBackground(Color.WHITE);
		lblByMorganDara.setEnabled(false);
		lblByMorganDara.setBounds(56, 171, 234, 29);
		frame.getContentPane().add(lblByMorganDara);
	}

}

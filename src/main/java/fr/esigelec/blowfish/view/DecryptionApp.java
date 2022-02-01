/**
 * @author Lothare KOUHEVI and DARA Morgan
 * Class represents decryption view 
 */

package fr.esigelec.blowfish.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.esigelec.blowfish.model.Blowfish;
import fr.esigelec.blowfish.model.MyFile;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DecryptionApp {

	public JFrame frameDecryption;
	private JTextField txtSecretKey;
	private JTextField txtFileName;
	private JTextField txtDestinationDirectory;
	private JButton chooseFileBtn;
	private JButton decipherBtn;
	private JButton destinationDirectoryBtn;
	
	private String locationOfFileToDecrypt = "";
	private String destinationDirectory = "";
	private String fileDestination = "";
	private String secretKey =  "";
	private String nameFile = "";
	
	private Blowfish blowfish = new Blowfish();	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptionApp window = new DecryptionApp();
					window.frameDecryption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DecryptionApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDecryption = new JFrame();
		frameDecryption.setTitle("Decryption");
		frameDecryption.setResizable(false);
		frameDecryption.setBounds(100, 100, 446, 399);
		frameDecryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDecryption.getContentPane().setLayout(null);
		frameDecryption.setFocusable(true);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SI_Crypt window = new SI_Crypt();
				window.frame.setVisible(true);
				frameDecryption.dispose();
			}
		});
		backBtn.setBackground(new Color(255, 69, 0));
		backBtn.setBounds(317, 11, 89, 23);
		frameDecryption.getContentPane().add(backBtn);
		
		JLabel lblByMorganDara = new JLabel("By Morgan DARA and Lothaire KOUHEVI");
		lblByMorganDara.setVerticalAlignment(SwingConstants.BOTTOM);
		lblByMorganDara.setEnabled(false);
		lblByMorganDara.setBackground(Color.WHITE);
		lblByMorganDara.setBounds(172, 320, 234, 29);
		frameDecryption.getContentPane().add(lblByMorganDara);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(23, 60, 383, 235);
		frameDecryption.getContentPane().add(panel);
		
		txtSecretKey = new JTextField();
		txtSecretKey.setColumns(10);
		txtSecretKey.setBounds(160, 11, 213, 31);
		panel.add(txtSecretKey);
		
		JLabel lblNewLabel = new JLabel("Enter your secret key");
		lblNewLabel.setBounds(10, 11, 127, 31);
		panel.add(lblNewLabel);
		
		txtFileName = new JTextField();
		txtFileName.setText("No file chosen !");
		txtFileName.setForeground(Color.GRAY);
		txtFileName.setEditable(false);
		txtFileName.setColumns(10);
		txtFileName.setBounds(10, 63, 197, 31);
		panel.add(txtFileName);
		
		chooseFileBtn = new JButton("Choose your file");
		chooseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBackground(Color.WHITE);
				frameDecryption.getContentPane().add(fileChooser, BorderLayout.NORTH);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers textes", "txt", "svg");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnVal = fileChooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	locationOfFileToDecrypt = fileChooser.getSelectedFile().getPath();
		        	nameFile = fileChooser.getSelectedFile().getName();
		        	
		        	txtFileName.setForeground(Color.MAGENTA);
		        	txtFileName.setText(nameFile);
		        	decipherBtn.setEnabled(true);
		        }	
				
			}
		});
		chooseFileBtn.setBounds(230, 63, 143, 31);
		panel.add(chooseFileBtn);
		
		decipherBtn = new JButton("Decipher your file");
		decipherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!destinationDirectory.equals("")) {
					if(validateSecretKey()) {
						JOptionPane.showMessageDialog(frameDecryption, "Decryption...");
						secretKey = txtSecretKey.getText().toString();						
						decryption();
						
						DecryptionApp window = new DecryptionApp();
						window.frameDecryption.setVisible(true);
						frameDecryption.dispose();
					}
					else
						JOptionPane.showMessageDialog(frameDecryption, "You must enter a secret key !", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frameDecryption, "You must enter the destination of the file deciphered !", "Warning", JOptionPane.WARNING_MESSAGE);

				
			}
		});
		decipherBtn.setEnabled(false);
		decipherBtn.setBackground(Color.GREEN);
		decipherBtn.setBounds(120, 191, 147, 33);
		panel.add(decipherBtn);
		
		destinationDirectoryBtn = new JButton("Choose destination directory");
		destinationDirectoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBackground(Color.WHITE);
				frameDecryption.getContentPane().add(fileChooser, BorderLayout.NORTH);				
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int returnVal = fileChooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	destinationDirectory = fileChooser.getSelectedFile().getPath();
		        	txtDestinationDirectory.setText(destinationDirectory);	
		        	txtDestinationDirectory.setForeground(Color.MAGENTA);
		        }
				
			}
		});
		destinationDirectoryBtn.setBounds(90, 149, 197, 23);
		panel.add(destinationDirectoryBtn);
		
		txtDestinationDirectory = new JTextField();
		txtDestinationDirectory.setText("No destination chosen !");
		txtDestinationDirectory.setForeground(Color.GRAY);
		txtDestinationDirectory.setEditable(false);
		txtDestinationDirectory.setColumns(10);
		txtDestinationDirectory.setBounds(10, 110, 363, 31);
		panel.add(txtDestinationDirectory);
	}
	
	/**
	 * Check secret key's validity
	 * @return true if secret key is valid
	 * 		   false else
	 */
	private boolean validateSecretKey() {
		boolean result = false;		
		String secretKey = this.txtSecretKey.getText().toString();
		if(!secretKey.equals(""))
			result = true;		
		return result;
	}
	
	/**
	 * Process to decrypt a file
	 */
	private void decryption() {		
		if(MyFile.validateFileChosen(locationOfFileToDecrypt)) {
			ArrayList<String> mainFileLines = MyFile.readFile(locationOfFileToDecrypt);
			ArrayList<String> decryptFileLines = this.blowfish.decrypt(mainFileLines, secretKey);
			fileDestination = nameFile.replace(".", "_Decrypted.");
			boolean success = MyFile.writeInFile(decryptFileLines, fileDestination, destinationDirectory);
			if(success)
				JOptionPane.showMessageDialog(frameDecryption, "File decrypted successfully ! It's available at this directory '" +destinationDirectory+ "' as " +fileDestination);
		}
	}
	
}

package fr.esigelec.blowfish.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.esigelec.blowfish.model.Blowfish;
import fr.esigelec.blowfish.model.MyFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

public class EncryptionApp {

	public JFrame frameEncryption;
	
	private JTextField txtSecretKey;
	private JTextField txtFileName;
	private JButton encipherBtn;
	private JButton destinationDirectoryBtn;
	private JTextField txtDestinationDirectory;
	private JLabel lblByMorganDara;
	private JButton backBtn;	
	
	private String locationOfFileToEncrypt = "";
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
					EncryptionApp window = new EncryptionApp();
					window.frameEncryption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EncryptionApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEncryption = new JFrame();
		frameEncryption.setTitle("Encryption");
		frameEncryption.setResizable(false);
		frameEncryption.setBounds(100, 100, 446, 399);
		frameEncryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEncryption.getContentPane().setLayout(null);
		frameEncryption.setFocusable(true);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 0, -430, 261);
		frameEncryption.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(28, 74, 383, 235);
		frameEncryption.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtSecretKey = new JTextField();
		txtSecretKey.setBounds(160, 11, 213, 31);
		panel.add(txtSecretKey);
		txtSecretKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter your secret key");
		lblNewLabel.setBounds(10, 11, 127, 31);
		panel.add(lblNewLabel);
		
		txtFileName = new JTextField();
		txtFileName.setForeground(Color.GRAY);
		txtFileName.setText("No file chosen !");
		txtFileName.setEditable(false);
		txtFileName.setColumns(10);
		txtFileName.setBounds(10, 63, 197, 31);
		panel.add(txtFileName);
		
		JButton chooseFileBtn = new JButton("Choose your file");
		chooseFileBtn.setBounds(230, 63, 143, 31);
		panel.add(chooseFileBtn);
		
		encipherBtn = new JButton("Encipher your file");
		encipherBtn.setEnabled(false);
		encipherBtn.setBounds(120, 191, 147, 33);
		panel.add(encipherBtn);
		encipherBtn.setBackground(Color.GREEN);
		
		destinationDirectoryBtn = new JButton("Choose destination directory");
		destinationDirectoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBackground(Color.WHITE);
				frameEncryption.getContentPane().add(fileChooser, BorderLayout.NORTH);				
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
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SI_Crypt window = new SI_Crypt();
				window.frame.setVisible(true);
				frameEncryption.dispose();
			}
		});
		backBtn.setBackground(new Color(255, 69, 0));
		backBtn.setBounds(321, 23, 89, 23);
		frameEncryption.getContentPane().add(backBtn);
		
		lblByMorganDara = new JLabel("By Morgan DARA and Lothaire KOUHEVI");
		lblByMorganDara.setVerticalAlignment(SwingConstants.BOTTOM);
		lblByMorganDara.setEnabled(false);
		lblByMorganDara.setBackground(Color.WHITE);
		lblByMorganDara.setBounds(177, 320, 234, 29);
		frameEncryption.getContentPane().add(lblByMorganDara);
		
		encipherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!destinationDirectory.equals("")) {
					if(validateSecretKey()) {
						JOptionPane.showMessageDialog(frameEncryption, "Encryption...");
						secretKey = txtSecretKey.getText().toString();						
						encryption();
						
						EncryptionApp window = new EncryptionApp();
						window.frameEncryption.setVisible(true);
						frameEncryption.dispose();
					}
					else
						JOptionPane.showMessageDialog(frameEncryption, "You must enter a secret key !", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frameEncryption, "You must enter the destination of the file enciphered !", "Warning", JOptionPane.WARNING_MESSAGE);
			
			}
		});
		
		chooseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBackground(Color.WHITE);
				frameEncryption.getContentPane().add(fileChooser, BorderLayout.NORTH);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers textes", "txt", "svg");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnVal = fileChooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	locationOfFileToEncrypt = fileChooser.getSelectedFile().getPath();
		        	nameFile = fileChooser.getSelectedFile().getName();
		        	
		        	txtFileName.setForeground(Color.MAGENTA);
		        	txtFileName.setText(nameFile);
		        	encipherBtn.setEnabled(true);
		        }		        	
				
			}
		});
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
	 * 	Process to encrypt a file
	 */
	private void encryption() {		
		if(MyFile.validateFileChosen(locationOfFileToEncrypt)) {
			ArrayList<String> mainFileLines = MyFile.readFile(locationOfFileToEncrypt);
			ArrayList<String> encryptFileLines = this.blowfish.encrypt(mainFileLines, secretKey);
			fileDestination = nameFile.replace(".", "_Encrypted.");
			MyFile.writeInFile(encryptFileLines, fileDestination, destinationDirectory);
			JOptionPane.showMessageDialog(frameEncryption, "File encrypted successfully ! It's available at this directory '" +destinationDirectory+ "' as " +fileDestination);
		}
	}
}

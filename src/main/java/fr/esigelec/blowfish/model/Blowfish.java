/**
 * @author Lothare KOUHEVI and DARA Morgan
 * Class represents blowfish class
 */

package fr.esigelec.blowfish.model;

import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Blowfish {
	
	//Get the logger for severals logs
	private static final Logger log = LogManager.getLogger("fr.esigelec.blowfish");

	
	/**
	 * Encrypt each string of a string's list
	 * @param toEncipher
	 * @param key
	 * @return ArrayList<String> that represents the lines enciphered
	 * 		   null if encrypting failed 
	 */
	public ArrayList<String> encrypt(ArrayList<String> toEncipher, final String key) {
		ArrayList<String> enciphered = new ArrayList<String>();
		try {
			log.info("Enciphering...");

			byte[] keyData = (key).getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] hasil;

			for(int i=0; i<toEncipher.size(); i++) {
				hasil = cipher.doFinal(toEncipher.get(i).getBytes());			
				enciphered.add(new String(Base64.getEncoder().encode(hasil)));
			}
			log.info("Enciphering successed !");

			return enciphered;

		} catch (Exception e) {
			log.info("Enciphering failed !");
			log.error(e.getMessage()+" : " +e.getCause());
			return null;
		}
	}

	
	/**
	 * Decrypt each string of a string's list
	 * @param toDecipher
	 * @param key
	 * @return ArrayList<String> that represents the lines deciphered
	 * 		   null if decryption failed 
	 */
	public ArrayList<String> decrypt(ArrayList<String> toDecipher, final String key) {
		ArrayList<String> deciphered = new  ArrayList<String>();
		try {
			log.info("Deciphering...");

			byte[] keyData = (key).getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] hasil;

			for(int i=0; i<toDecipher.size(); i++) {
				hasil = cipher.doFinal(Base64.getDecoder().decode(toDecipher.get(i)));			
				deciphered.add(new String(hasil));
			}
			log.info("Deciphering successed !");

			return deciphered;

		} catch (Exception e) {
			log.info("Deciphering failed !");
			log.error(e.getMessage()+" : " +e.getCause());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Deciphering failed !", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

}

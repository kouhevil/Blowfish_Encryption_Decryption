package fr.esigelec.blowfish;

import java.util.ArrayList;

import fr.esigelec.blowfish.model.Blowfish;

public class BlowfishTest {

	public static void main(String[] args) {
		ArrayList<String> testList = new  ArrayList<String>();		
		testList.add("Voici ce que je dit !");		
		testList.add("Voici ce que je n'est pas dit !");
		testList.add("Voici ce que je dit !");
		testList.add("Voici ce que je n'est pas dit !");

		String key = "morgan_kouhevi_SI_2022";
		Blowfish blowfish = new Blowfish();

		ArrayList<String> wordCiphered = (ArrayList<String>) blowfish.encrypt(testList, key),
				wordDeciphered = blowfish.decrypt(wordCiphered, key);
		System.out.println(testList);
		System.out.println(wordCiphered);
		System.out.println(wordDeciphered);
	}

}

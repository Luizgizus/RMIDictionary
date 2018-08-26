package dicionario;

import java.rmi.Naming;
public class Client {
	public static void main(String[] args) {
		try {
			IntrServidor connectionOfDictionary = (IntrServidor)Naming.lookup("rmi://192.168.0.18/DictionaryService");
			
			System.out.println(connectionOfDictionary.getMeaningByWord("batata"));
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}

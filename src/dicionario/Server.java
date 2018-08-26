package dicionario;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IntrServidor {
	private static final long serialVersionUID = 1L;
	protected Server() throws RemoteException {}

	@Override
	public String getMeaningByWord(String word) throws RemoteException {
		Meaning m = new Meaning(word, null);
		try {
			long positionMeaning = ArchiveBase.getPositionMeaning(word);
			if(positionMeaning != -1) {
				String meaning = ArchiveBase.getMeaningByPosition(positionMeaning);
				m.setDescription(meaning);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		if(m.hasMeaning()) {
			return m.toString();
		} else {
			return null;
		}
	}

	@Override
	synchronized public String AddNewMeaning(String word, String meaning) throws RemoteException {
		try {
			String alreadyHasMeaning = getMeaningByWord(word);
			if(alreadyHasMeaning == null) {
				long position = ArchiveBase.addMeaning(meaning);
				ArchiveBase.addWord(word + ";" + position + ";0");
			} else {
				return "Palavra já existe nesse dicionário";
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return "Significado adicionada ao dicionário";
	}

	@Override
	synchronized public String removeMeaning(String word) throws RemoteException {
		try {
			String hasMeaning = getMeaningByWord(word);
			if(hasMeaning == null) {
				return null;
			} else {
				ArchiveBase.removeWord(word);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return "Significado removido do dicionário";
	}
	
	public static void main(String[] args) {
		try {
			IntrServidor dictionaryServer = new Server();
			Naming.rebind("DictionaryService", dictionaryServer);
			System.out.println("servidor startado");
		}
		catch (Exception e) { System.err.println(e); }
	}
}

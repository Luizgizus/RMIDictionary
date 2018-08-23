package dicionario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements IntrServidor {

	protected Servidor() throws RemoteException {}

	@Override
	public String getMeaningByWord(String word) throws RemoteException {
		return null;
	}

	@Override
	public String AddNewMeaning(String word, String meaning) throws RemoteException {
		return null;
	}

	@Override
	public String removeMeaning(String word) throws RemoteException {
		return null;
	}

}

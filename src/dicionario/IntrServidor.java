package dicionario;

import java.rmi.*;

public interface IntrServidor extends Remote {

	public abstract String getMeaningByWord(String word) throws RemoteException;
	public abstract String AddNewMeaning(String word, String meaning) throws RemoteException;
	public abstract String removeMeaning(String word) throws RemoteException;
	
}
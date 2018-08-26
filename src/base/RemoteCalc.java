package base;


import java.rmi.*;
public interface RemoteCalc extends Remote {
	public abstract float soma(float a, float b) throws RemoteException;
	public abstract float sub(float a, float b) throws RemoteException;
	public abstract float multi(float a, float b) throws RemoteException;
	public abstract float divi(float a, float b) throws RemoteException;
}
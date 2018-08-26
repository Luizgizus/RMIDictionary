package base;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class RemoteCalcImpl extends UnicastRemoteObject implements RemoteCalc {
	private static final long serialVersionUID = 1L;
	public RemoteCalcImpl() throws RemoteException {}
	
	public float soma(float a, float b) throws RemoteException {
		return a + b;
	}
	
	public float sub(float a, float b) throws RemoteException {
		return a - b;
	}
	
	public float multi(float a, float b) throws RemoteException {
		return a * b;
	}
	
	public float divi(float a, float b) throws RemoteException {
		return a / b;
	}
	
	public static void main(String[] args) {
		try {
			RemoteCalc calcServer = new RemoteCalcImpl();
			Naming.rebind("CalculatorService", calcServer);
		}
		catch (Exception e) { System.err.println(e); }
	}
}
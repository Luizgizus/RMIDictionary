package base;

import java.rmi.Naming;
public class CalculatorClient {
	public static void main(String[] args) {
		try {
			RemoteCalc c = (RemoteCalc)Naming.lookup("rmi://servidor/CalculatorService");
			System.out.println( c.sub(4, 3) );
			System.out.println( c.soma(4, 5) );
			System.out.println( c.multi(3, 6) );
			System.out.println( c.divi(9, 3) );
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}

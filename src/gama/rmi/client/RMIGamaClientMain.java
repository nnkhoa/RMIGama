package gama.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import gama.rmi.server.RMIGamaServerInterface;

public class RMIGamaClientMain {
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
		String RMIGamaServer = "rmi://127.0.0.1:5099/gamaServer";
		RMIGamaServerInterface gamaServer = (RMIGamaServerInterface) Naming.lookup(RMIGamaServer);
		
		new Thread(new RMIGamaClient(gamaServer)).start();
	}
}

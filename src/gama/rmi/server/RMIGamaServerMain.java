package gama.rmi.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIGamaServerMain {
	public static void main(String args[]) throws UnknownHostException, RemoteException{
		System.out.println("--------Info--------");
		
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		System.out.println("Host Adress: " + hostAddress);
		System.out.println("Server Hostname: " + System.getProperties().getProperty("java.rmi.server.hostname"));
		System.out.println("Host Name: "+ InetAddress.getLocalHost().getHostName());
        System.out.println("Host FQDN:  "+ InetAddress.getByName(hostAddress).getHostName());
        System.out.println("Local Host Name: " + System.getProperties().getProperty("java.rmi.server.useLocalHostName"));
        
        System.out.println("--------------------");
        System.out.println("creating Registry...");
        
        Registry registry = LocateRegistry.createRegistry(5099);
        
        System.out.println("binding Server");
        
        registry.rebind("gamaServer", new RMIGamaServer());
        
        System.out.println("Ready!");
	}
}

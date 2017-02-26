package gama.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gama.rmi.client.RMIGamaClientInterface;

public interface RMIGamaServerInterface extends Remote {
	public void registerClient(RMIGamaClientInterface client) throws RemoteException;
	public void getTurn(String clientName) throws RemoteException;
	public void checkStatus() throws RemoteException;
	public void sendStringToClient(String string) throws RemoteException;
	public String setTempString(String string) throws RemoteException;
	public int getCurrentPos() throws RemoteException;
}

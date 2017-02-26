package gama.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIGamaClientInterface extends Remote {
	public boolean isTurn() throws RemoteException;
	public void isMyTurnNow() throws RemoteException;
	public void setTurn(boolean myTurn) throws RemoteException;
	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
	public void recieveStringFromServer(String string) throws RemoteException;
	
}

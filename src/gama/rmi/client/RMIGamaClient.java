package gama.rmi.client;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import gama.rmi.server.RMIGamaServerInterface;

//to be remove Runnable

public class RMIGamaClient extends UnicastRemoteObject implements RMIGamaClientInterface, Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RMIGamaServerInterface RMIGamaServer;
	private boolean myTurn;
	private String name;
	private String serializedString;
	

	protected RMIGamaClient(RMIGamaServerInterface RMIGamaServer) throws RemoteException {
		this.name = new BigInteger(130, new SecureRandom()).toString(32);
		this.RMIGamaServer = RMIGamaServer;
		RMIGamaServer.registerClient(this);
		this.myTurn = false;
	}

	@Override
	public boolean isTurn() throws RemoteException {
		// TODO Auto-generated method stub
		return myTurn;
	}

	@Override
	public void isMyTurnNow() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("It's my turn!!!");
	}

	@Override
	public void setTurn(boolean myTurn) throws RemoteException {
		// TODO Auto-generated method stub
		this.myTurn = myTurn;
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void recieveStringFromServer(String string) throws RemoteException {
		// TODO Auto-generated method stub
		this.serializedString = string;
		System.out.println(this.getName() + ": " + this.serializedString);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//to be remove
	public void run(){
		try {
			RMIGamaServer.checkStatus();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				if (isTurn()){
					this.isMyTurnNow();
					TimeUnit.SECONDS.sleep(3);
					this.setTurn(!myTurn);
					RMIGamaServer.getTurn(this.getName());
				}else{
					System.out.println("In waiting");
					TimeUnit.SECONDS.sleep(4);
				}
			} catch (RemoteException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	}

}

package gama.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gama.rmi.client.RMIGamaClientInterface;

public class RMIGamaServer extends UnicastRemoteObject implements RMIGamaServerInterface{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<RMIGamaClientInterface> gamaClients;
	private String tempString;

	public RMIGamaServer() throws RemoteException {
		gamaClients = new ArrayList<RMIGamaClientInterface>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void registerClient(RMIGamaClientInterface client) throws RemoteException {
		// TODO Auto-generated method stub
		this.gamaClients.add(client);
	}

	@Override
	public void getTurn(String clientName) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("client " + clientName + " has finished");
		
		int pos = this.findByName(clientName);
		
		System.out.println("Current position in the list: " + pos);
		System.out.println("Current state: " + gamaClients.get(pos).isTurn());
		
		if(pos == (gamaClients.size() - 1)){
			gamaClients.get(0).setTurn(true);
			System.out.println("Client " + gamaClients.get(0).getName() + " is Next");
		}else{
			gamaClients.get(pos + 1).setTurn(true);
			System.out.println("Client " + gamaClients.get(pos + 1).getName() + " is Next");
		}
	}

	//find machine by its name
	private int findByName(String clientName) {
		int pos = 0;
		for (int i = 0; i < gamaClients.size(); i = i + 1){
			try {
				if(gamaClients.get(i).getName().equals(clientName)){
					pos = i;
					break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}

	//check if enough machines has connect
	//if not, sleep, else start 
	@Override
	public void checkStatus() throws RemoteException {
		// for now, only 2 clients are considered
		// will need to change this in the future for the greater good
		while(gamaClients.size() != 2 ){
			
			System.out.println("Waiting for other machines to connect!");
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//print the current connected clients to the server while waiting
			
			System.out.println("Current connected clients: ");
			for(int i = 0; i < gamaClients.size(); i = i + 1){
				System.out.println("Client number: " + i);
				System.out.println("Client name: " + gamaClients.get(i).getName());
			}
			
		}
		
		//set the simulation to start with client 0
		if(!gamaClients.get(0).isTurn()){
			gamaClients.get(0).setTurn(true);
		}
	}

	@Override
	public void sendStringToClient(String string) throws RemoteException {
		// TODO Auto-generated method stub
		int pos = this.getCurrentPos();
		
		gamaClients.get(pos).recieveStringFromServer(string);
	}

	@Override
	public String setTempString(String string) throws RemoteException {
		// TODO Auto-generated method stub
		return this.tempString = string;
	}

	//get the current running machine's number in the array
	@Override
	public int getCurrentPos() throws RemoteException {
		// TODO Auto-generated method stub
		int pos = 0;
		
		for(int i = 0; i < gamaClients.size(); i= i + 1){
			if(gamaClients.get(i).isTurn()){
				pos = i;
			}
		}
		return pos;
	}

}

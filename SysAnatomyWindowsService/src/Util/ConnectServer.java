package Util;

import org.json.simple.JSONObject;
import org.apache.logging.log4j.core.Logger;

public class ConnectServer implements Runnable  {
	private static int gintCompletedCount = 0;

	public ConnectServer()
	{
	/*	synchronized (this) {
			while (GlobalObjects.gobjConnectToServer.getCompletedCount() != 3) {
				GlobalObjects.gobjConnectToServer.sendJsonToServer(lobjJsonMemData);
			}
		}*/
	}
	
	public int getCompletedCount()
	{
		return gintCompletedCount;
	}
	
	

	public void sendJsonToServer(JSONObject lobjJsonMemData) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}

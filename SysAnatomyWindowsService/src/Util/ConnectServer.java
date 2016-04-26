package Util;

import org.json.simple.JSONObject;

import WindowsService.MemoryData;

import org.apache.log4j.Logger;

public class ConnectServer implements Runnable  {
	private static final Logger LOGGER = Logger.getLogger(MemoryData.class.getName());
	
	public ConnectServer()
	{
		new Thread(this).start();
	}

	public void sendJsonToServer(JSONObject pobjJsonToSend) {
		// TODO Auto-generated method stub
		try{
		//Code to send serialized Json to the server
		pobjJsonToSend.toJSONString();
		}catch(Exception ex)
		{
			LOGGER.error("Exception in sendJsonToServer : "+ ex.getMessage());
		}
	}

	public void run() {
		synchronized (GlobalObjects.larrlstJson) {
			while(!GlobalObjects.larrlstJson.isEmpty())
				sendJsonToServer(GlobalObjects.larrlstJson.remove(0));
		}
	}
}

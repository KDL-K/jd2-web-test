package by.htp.ts.service;

import by.htp.ts.service.impl.ClientServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory service=new ServiceFactory();
	
	private final ClientService clientS=new ClientServiceImpl();
	
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return service;
	}
	public ClientService getClientS() {
		return clientS;
	}

}

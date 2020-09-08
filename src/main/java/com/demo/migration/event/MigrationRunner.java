package com.demo.migration.event;

import com.demo.migration.command.core.ClientRequest;
import com.demo.migration.command.core.Invoker;
import com.demo.migration.command.type.CommandID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MigrationRunner implements ApplicationRunner {

	@Autowired
	private Invoker invoker;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ClientRequest clientRequest = new ClientRequest();
		clientRequest.setCommandID(CommandID.MC0001);
		invoker.invoke(clientRequest);
	}

}

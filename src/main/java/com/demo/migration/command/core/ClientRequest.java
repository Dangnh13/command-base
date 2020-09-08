package com.demo.migration.command.core;

import java.util.Map;

import com.demo.migration.command.type.CommandID;

public class ClientRequest {

	private CommandID commandID;
	private Map<String, String> metaData;

	public CommandID getCommandID() {
		return commandID;
	}

	public void setCommandID(CommandID commandID) {
		this.commandID = commandID;
	}

	public Map<String, String> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}

}

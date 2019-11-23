package by.htp.ts.command;

import java.util.HashMap;
import java.util.Map;
import by.htp.ts.command.impl.*;

public final class CommandProvider {
	private Map<CommandName, Command> commands=new HashMap<CommandName,Command>();
	
	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
	}
	
	public Command getCommand(String commandStr) {
		CommandName commandName=CommandName.valueOf(commandStr.toUpperCase());
		return commands.get(commandName);
	}

}

package eu.blackspectrum.commandsigns.handler;

import eu.blackspectrum.commandsigns.SignExecutor;

public class WaitHandler extends Handler {

	@Override
	public void handle(SignExecutor e, String command, boolean silent,
			boolean negate) {
		if (command.startsWith("%")) {
			double amount = 0;
			try {
				amount = Double.parseDouble(command.substring(1));
			} catch (NumberFormatException ex) {
				return;
			}
			e.setWait(amount);
		}
	}

}

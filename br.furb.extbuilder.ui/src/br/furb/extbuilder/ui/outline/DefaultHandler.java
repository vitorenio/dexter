package br.furb.extbuilder.ui.outline;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class DefaultHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		event.getApplicationContext();
		System.out.println("funcionou!");
		return null;
	}

}

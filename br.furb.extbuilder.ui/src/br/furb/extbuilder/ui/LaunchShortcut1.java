package br.furb.extbuilder.ui;

import java.io.File;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import br.furb.extbuilder.ui.editor.preview.Preview;

public class LaunchShortcut1 implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		openBrowser();
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		openBrowser();
	}

	private void openBrowser() {
		Preview.openPreviewBrowser(new File("/home/wendel/workspace/dexter/br.furb.extbuilder.ui/preview.js"));
	}

}

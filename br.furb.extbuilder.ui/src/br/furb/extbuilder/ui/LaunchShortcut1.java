package br.furb.extbuilder.ui;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;

import br.furb.extbuilder.core.PreviewerCore;
import br.furb.extbuilder.ui.editor.PerspectiveEditor;
import br.furb.extbuilder.ui.editor.preview.Preview;

public class LaunchShortcut1 implements ILaunchShortcut {

	private PreviewerCore previewer = new PreviewerCore();
	
	public LaunchShortcut1(){
		//previewer.
	}
	
	@Override
	public void launch(ISelection selection, String mode) {
		File file = PreviewerCore.getHtmlForPreview(selection);
		
		openBrowser(file);
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		openBrowser();
	}

	private void openBrowser() {
		openBrowser(null);
	}
	
	private void openBrowser(File file) {
		if (file == null){
			throw new UnsupportedOperationException("Operaçao nao suportada.");
			//Preview.openPreviewBrowser(new File(ResourcesPlugin.getWorkspace().getRoot().getProject("").getLocation().toOSString()+"/preview.js"));
		}else{
			Preview.openPreviewBrowser(file);
		}
	}

}

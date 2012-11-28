package br.furb.extbuilder.ui.editor.preview;

import java.io.File;
import java.io.IOException;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Classe responsavel por realizar um preview em um navegador externo ao Eclipse.
 * 
 * @author vitor
 *
 */
public final class Preview {

	public static final void openPreviewBrowser(final File previewFile) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				try {
					IWebBrowser browser;
					try {
						browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL,"chrome", "chrome", "Preview");
						browser.openURL(previewFile.toURI().toURL());
					} catch (PartInitException e) {
						throw new RuntimeException(e);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			}
		});
	}
}

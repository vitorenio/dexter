package br.furb.extbuilder.ui.editor.preview;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.ide.ResourceUtil;
import org.osgi.framework.Bundle;

import br.furb.extbuilder.core.PreviewerCore;

public final class Preview {

	//private static PreviewerCore previewer = new PreviewerCore();

	public static final void openPreviewBrowser(final File previewFile) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				//Bundle bundle = Platform.getBundle("br.furb.extbuilder.ui");
				//URL fileURL = bundle.getEntry("preview/");
				try {
					/*File previewDir = new File(FileLocator.resolve(fileURL).toURI());
					previewer.externalizePreviewDir(previewDir);

					File newPreviewFile = previewer.externalizePreviewFile(previewFile);
*/
					//IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("preview");
					//browser.openURL(INDEX_PREVIEW_FILE.toURI().toURL());
					
					IWebBrowser browser;
					try {
						browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL,"chrome", "chrome", "Preview");
						
						browser.openURL(previewFile.toURI().toURL());
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				/*} catch (URISyntaxException e1) {
					e1.printStackTrace();*/
				} catch (IOException e1) {
					e1.printStackTrace();
				} /*catch (PartInitException e) {
					e.printStackTrace();
				}*/
			}
		});
	}


}

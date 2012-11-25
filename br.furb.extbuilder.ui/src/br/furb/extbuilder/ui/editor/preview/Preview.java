package br.furb.extbuilder.ui.editor.preview;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.osgi.framework.Bundle;

public final class Preview {

	private static final File PREVIEW_DEST_DIR = new File("/home/wendel/temp/extPreview");
	private static final File INDEX_PREVIEW_FILE = new File(PREVIEW_DEST_DIR, "preview.html");

	public static final void openPreviewBrowser(final File previewFile) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				Bundle bundle = Platform.getBundle("br.furb.extbuilder.ui");
				URL fileURL = bundle.getEntry("preview/");
				try {
					File previewDir = new File(FileLocator.resolve(fileURL).toURI());
					externalizePreviewDir(previewDir);

					externalizePreviewFile(previewFile);

					IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("preview");
					browser.openURL(INDEX_PREVIEW_FILE.toURI().toURL());
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void externalizePreviewFile(File file) throws IOException {
		File destFile = new File(PREVIEW_DEST_DIR, "preview.js");
		FileUtils.copyFile(file, destFile);
	}

	private static void externalizePreviewDir(File srcDir) throws IOException {
		if (!PREVIEW_DEST_DIR.exists()) {
			PREVIEW_DEST_DIR.mkdirs();
		}
		FileUtils.copyDirectory(srcDir, PREVIEW_DEST_DIR, true);
	}

}

package br.furb.extbuilder.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.osgi.framework.Bundle;

public class PreviewerCore {

	private static File previewDestDir = new File(ResourcesPlugin.getPlugin().getStateLocation().append("tmp/extPreview").toOSString());
	//private static File indexPreviewFile;
	
	private static Map<String, File> cachedPreviews = new HashMap<String, File>();
	

	private static File externalizePreviewFile(File file, ISelection selection) throws IOException {
		File destFile = new File(getPreviewDestDir(selection), "/preview.js");
		FileUtils.copyFile(file, destFile);
		
		return destFile;
	}
	
	private static String toJSExtension(File toConvert){
		return FilenameUtils.removeExtension(toConvert.getAbsolutePath())+".js";
	}

	private static void externalizePreviewDir(File srcDir, ISelection selection) throws IOException {
		if (!getPreviewDestDir(selection).exists()) {
			getPreviewDestDir(selection).mkdirs();
		}
		FileUtils.copyDirectory(srcDir, getPreviewDestDir(selection), true);
	}

	private static File getPreviewDestDir(ISelection selection) {
		TreeSelection treeSelection = ((TreeSelection)selection);
		
		IFile file =((IFile)treeSelection.getPaths()[0].getLastSegment());
		
		String prjName = file.getProject().getName();
		String fileRelativePath = file.getProjectRelativePath().toOSString();
		
		return new File(previewDestDir, FilenameUtils.removeExtension(new Path(prjName).append(fileRelativePath).toOSString()));
	}

/*	public File getIndexPreviewFile() {
		return indexPreviewFile;
	}

	public void setIndexPreviewFile(File indexPreviewFile) {
		this.indexPreviewFile = indexPreviewFile;
	}*/

	public static File getHtmlForPreview(ISelection selection) {
		String previewsDestDir = getPreviewDestDir(selection).getAbsolutePath();
		if (cachedPreviews.containsKey(previewsDestDir)){
			//sobreescrevemos o JS para atualizar os componentes...
			try {
				File jsFile = getJsFile(selection);
				File jsDest = new File(previewsDestDir+"/preview.js");
				
				jsDest.delete();
				
				FileUtils.copyFile(jsFile, jsDest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cachedPreviews.get(previewsDestDir);
		}
		try {
			//pega o arquivo Js que sera copiado para o diretorio temporario
			File previewJsFile = getJsFile(selection);
			
			
			Bundle bundle = Platform.getBundle("br.furb.extbuilder.ui");
			URL fileURL = bundle.getEntry("preview/");
			
			//copia um diretorio ext funcional com as libs do ext para um diretorio temporario do plugin
			File previewSrcDir = new File(FileLocator.resolve(fileURL).toURI());
			externalizePreviewDir(previewSrcDir, selection);
			
			//copia o arquivo .js que sera importado para dentro do preview.html
			externalizePreviewFile(previewJsFile, selection);
			
			File htmlForPreview = getIndexPreviewFile(selection); 
			cachedPreviews.put(previewsDestDir, htmlForPreview);
			return htmlForPreview;
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}

	/**
	 * pega o arquivo Js que sera copiado para o diretorio temporario
	 * 
	 * @param selection
	 * @return
	 */
	private static File getJsFile(ISelection selection) {
		
		TreeSelection treeSelection = (TreeSelection)selection;
		IFile lastSegment = (IFile)treeSelection.getPaths()[0].getLastSegment();
		File previewJsFile = new File(lastSegment.getLocation().toOSString());
		return new File(toJSExtension(previewJsFile));
	}

	private static File getIndexPreviewFile(ISelection selection) {
		return  new File(getPreviewDestDir(selection), "preview.html");
	}

}

package br.furb.extbuilder.ui.editor;


import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.dialogs.IPropertyPageContributor;
import org.eclipse.ui.internal.views.properties.tabbed.TabbedPropertyViewPlugin;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;

import br.furb.extbuilder.ui.component.Component;
import br.furb.extbuilder.ui.outline.ExtOutlinePage;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class PerspectiveEditor extends MultiPageEditorPart implements IResourceChangeListener{

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;

	/**
	 * Creates a multi-page editor example.
	 */
	public PerspectiveEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		
		
		//getEditorInput();
		//getEditorInput().
	}
	/**
	 * Creates page 0 of the multi-page editor,
	 * which contains a text editor.
	 */
	void createPage0() {
		Composite container = new Composite(getContainer(), SWT.NONE);
		container.setLayout(new FillLayout());
		
		
		Browser browser = new Browser(container, SWT.NONE);
		if(!browser.setUrl("http://www.google.com/")){
			System.out.println("nao conectou....");
		}
		//browser.setText("<html><body><b>Funfou</b></body></html>");
		browser.refresh();
		browser.setLayout(new FillLayout());
		
	
		int index = addPage(container);

		setPageText(index, "Preview");
		createPaletteControls(container);
		/*
		createActions();
		initializeToolBar();
		initializeMenu();
		*/
	}
	private void createPaletteControls(Composite container) {
		// 		Composite container = new Composite(parent, SWT.NONE);
		
		//Composite container = new Composite(parent, SWT.NONE);

		Composite composite_1 = new Composite(container, SWT.RIGHT);
		composite_1.setBounds(0, 0, 300, 468);

		ExpandBar expandBar = new ExpandBar(composite_1, SWT.NONE);
		expandBar.setSize(158, 224);
		expandBar.setSpacing(0);

		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("Controls");

		Composite composite = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite);
		xpndtmNewExpanditem.setHeight(200);
		composite.setLayout(null);

		createNewButton(composite);
		createNewLabel(composite);
		createNewText(composite);
		createNewForm(composite);
		createNewCheckBox(composite);
		createNewRadio(composite);
		createNewPanel(composite);

	}
	private void createNewPanel(Composite composite) {
		Button btnPanel = new Button(composite, SWT.NONE);
		btnPanel.setBounds(5, 96, 65, 25);
		btnPanel.setText("Panel");
		/* drag */
		// Allow data to be copied or moved from the drag source
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		DragSource source = new DragSource(btnPanel, operations);

		// Provide data in Text format
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		source.setTransfer(types);

		source.addDragListener(new EditorDragSourceListener(new br.furb.extbuilder.ui.component.Panel()));
	}
	private void createNewRadio(Composite composite) {
		Button btnRadio = new Button(composite, SWT.NONE);
		btnRadio.setBounds(76, 5, 65, 25);
		btnRadio.setText("Radio");
		btnRadio.setEnabled(false);
	}
	private void createNewCheckBox(Composite composite) {
		Button btnCheckbox = new Button(composite, SWT.NONE);
		btnCheckbox.setBounds(76, 34, 65, 25);
		btnCheckbox.setText("Checkbox");
		btnCheckbox.setEnabled(false);
	}
	private void createNewForm(Composite composite) {
		Button btnForm = new Button(composite, SWT.NONE);
		btnForm.setBounds(76, 65, 65, 25);
		btnForm.setText("Form");
		btnForm.setEnabled(false);
	}
	private void createNewText(Composite composite) {
		Button btnTextArea = new Button(composite, SWT.NONE);
		btnTextArea.setBounds(5, 65, 65, 25);
		btnTextArea.setText("Text");
		/* drag */
		// Allow data to be copied or moved from the drag source
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		DragSource source = new DragSource(btnTextArea, operations);

		// Provide data in Text format
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		source.setTransfer(types);

		source.addDragListener(new EditorDragSourceListener(new br.furb.extbuilder.ui.component.Text()));
	}
	private void createNewLabel(Composite composite) {
		Button btnLabel = new Button(composite, SWT.NONE);
		btnLabel.setBounds(5, 34, 65, 25);
		btnLabel.setText("Label");
		btnLabel.setEnabled(false);
	}
	private void createNewButton(Composite composite) {
		final Button btnButton = new Button(composite, SWT.NONE);
		btnButton.setBounds(5, 5, 65, 25);
		btnButton.setText("Button");
		/* drag */
		// Allow data to be copied or moved from the drag source
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		DragSource source = new DragSource(btnButton, operations);

		// Provide data in Text format
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		source.setTransfer(types);

		source.addDragListener(new EditorDragSourceListener(new br.furb.extbuilder.ui.component.Button()));
	}
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	void createPage1() {

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		
		

		Button btnBotaoTemporarioQue = new Button(composite, SWT.NONE);
		btnBotaoTemporarioQue.setBounds(416, 205, 156, 94);
		btnBotaoTemporarioQue.setText("Aqui acontece a magica");
		
		//editor = new TextEditor();
		
		int index = addPage(composite);
		


		setPageText(index, "Source");
		
		
	}
	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		//createPage2();

	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		//setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText =
			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer =
			new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
	
	@Override
	public Object getAdapter(Class adapter) {
	    if (IContentOutlinePage.class.equals(adapter)) {
	        return new ExtOutlinePage(); 
	    }
	    return super.getAdapter(adapter);
	}
	
	private class EditorDragSourceListener implements DragSourceListener{
		
		private Component component;
		
		public <T extends Component> EditorDragSourceListener(T component){
			this.component = component;
		}
		
		public void dragStart(DragSourceEvent event) {
			event.doit = true;
		}

		public void dragSetData(DragSourceEvent event) {
			// Provide the data of the requested type.
			if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
				event.data = component.getClass().getSimpleName();
			}
		}

		public void dragFinished(DragSourceEvent event) {
			// nothing to do here
		}
		
	}
}

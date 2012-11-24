package br.furb.extbuilder.ui.prototipo;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class PrototypeEditorWithPalette extends ViewPart {

	public static final String ID = "br.furb.extbuilder.ui.editor.testview"; //$NON-NLS-1$
	private Text txtAsd;

	public PrototypeEditorWithPalette() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Button btnButton = new Button(container, SWT.NONE);
		btnButton.setBounds(416, 10, 75, 25);
		btnButton.setText("Button");
		
		Button btnLabel = new Button(container, SWT.NONE);
		btnLabel.setText("Label");
		btnLabel.setBounds(416, 41, 75, 25);
		
		Button btnForm = new Button(container, SWT.NONE);
		btnForm.setText("Form");
		btnForm.setBounds(416, 72, 75, 25);
		
		Button btnEditbox = new Button(container, SWT.NONE);
		btnEditbox.setText("EditBox");
		btnEditbox.setBounds(416, 103, 75, 25);
		
		Button btnCombo = new Button(container, SWT.NONE);
		btnCombo.setText("Combo");
		btnCombo.setBounds(416, 134, 75, 25);
		
		Button button = new Button(container, SWT.NONE);
		button.setText("Combo");
		button.setBounds(497, 134, 75, 25);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setText("Button");
		button_1.setBounds(497, 10, 75, 25);
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.setText("Label");
		button_2.setBounds(497, 41, 75, 25);
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.setText("Form");
		button_3.setBounds(497, 72, 75, 25);
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.setText("EditBox");
		button_4.setBounds(497, 103, 75, 25);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(10, 10, 400, 448);
		
		TabItem tbtmDesign = new TabItem(tabFolder, SWT.NONE);
		tbtmDesign.setText("Design");
		
		Browser browser = new Browser(tabFolder, SWT.NONE);
		browser.setUrl("www.ddd.com");
		browser.setText("browser");
		tbtmDesign.setControl(browser);
		
		TabItem tbtmSource = new TabItem(tabFolder, SWT.NONE);
		tbtmSource.setText("Source");
		
		txtAsd = new Text(tabFolder, SWT.BORDER);
		txtAsd.setText("<fonte javascript>");
		tbtmSource.setControl(txtAsd);
		
		Button btnBotaoTemporarioQue = new Button(container, SWT.NONE);
		btnBotaoTemporarioQue.setBounds(416, 205, 156, 94);
		btnBotaoTemporarioQue.setText("Aqui acontece a magica");

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}

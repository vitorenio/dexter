package br.furb.extbuilder.ui.palette;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class PaletteView extends ViewPart {

	public static final String ID = "br.furb.extbuilder.ui.palette.PaletteView"; //$NON-NLS-1$

	public PaletteView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setBounds(0, 0, 594, 468);
		
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
		
		Button btnButton = new Button(composite, SWT.NONE);
		btnButton.setBounds(5, 5, 65, 25);
		btnButton.setText("Button");
		
		Button btnLabel = new Button(composite, SWT.NONE);
		btnLabel.setBounds(5, 34, 65, 25);
		btnLabel.setText("Label");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(5, 65, 65, 25);
		btnNewButton.setText("Text Area");
		
		Button btnForm = new Button(composite, SWT.NONE);
		btnForm.setBounds(76, 65, 65, 25);
		btnForm.setText("Form");
		
		Button btnCheckbox = new Button(composite, SWT.NONE);
		btnCheckbox.setBounds(76, 34, 65, 25);
		btnCheckbox.setText("Checkbox");
		
		Button btnRadio = new Button(composite, SWT.NONE);
		btnRadio.setBounds(76, 5, 65, 25);
		btnRadio.setText("Radio");

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

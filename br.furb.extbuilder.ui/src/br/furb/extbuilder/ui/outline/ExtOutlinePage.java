package br.furb.extbuilder.ui.outline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.AbstractAction;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.NewWizardDropDownAction;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;

import br.furb.extbuilder.ui.component.Component;
import br.furb.extbuilder.ui.component.Panel;

public class ExtOutlinePage extends ContentOutlinePage {

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		TreeViewer tree = getTreeViewer();
		tree.addSelectionChangedListener(this);
		tree.setContentProvider(new ExtOutlineContentProvider());
		tree.setLabelProvider(new ExtOutlineLabelProvider());

		// Configure the context menu.
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$

		menuMgr.add(new Separator()); //$NON-NLS-1$

		menuMgr.add(new AbstractAction() {

			@Override
			public void setToolTipText(String text) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setText(String text) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setMenuCreator(IMenuCreator creator) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setImageDescriptor(ImageDescriptor newImage) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setId(String id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setHoverImageDescriptor(ImageDescriptor newImage) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setHelpListener(HelpListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setEnabled(boolean enabled) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setDisabledImageDescriptor(ImageDescriptor newImage) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setDescription(String text) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setChecked(boolean checked) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setActionDefinitionId(String id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setAccelerator(int keycode) {
				// TODO Auto-generated method stub

			}

			@Override
			public void runWithEvent(Event event) {
				TreeSelection ts = (TreeSelection) getSelection();
				
				List<Component> list = ts.toList();
				
				for(Component cmp:list){
					
					System.out.println("it_"+ cmp.getId());
				}
				System.out.println("done!");

			}

			@Override
			public void run() {


			}

			@Override
			public boolean isHandled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isChecked() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getToolTipText() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getText() {

				return "Cobaia 1";
			}

			@Override
			public int getStyle() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public IMenuCreator getMenuCreator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ImageDescriptor getImageDescriptor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ImageDescriptor getHoverImageDescriptor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HelpListener getHelpListener() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ImageDescriptor getDisabledImageDescriptor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getActionDefinitionId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getAccelerator() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		final Menu menu = menuMgr.createContextMenu(parent);

		tree.getTree().setMenu(menu);
		// Be sure to register it so that other plug-ins can add actions.
		getSite().registerContextMenu(
				"org.eclipse.ui.examples.readmetool.outline", menuMgr, tree); //$NON-NLS-1$

		Panel root = new Panel();
		root.setName("root");

		Panel child1 = new Panel();
		child1.setName("child1");

		root.addChild(child1);

		Panel child2 = new Panel();
		child2.setName("child2");
		child1.addChild(child2);

		tree.setInput(root);

		getSite().setSelectionProvider(tree);
	}

}

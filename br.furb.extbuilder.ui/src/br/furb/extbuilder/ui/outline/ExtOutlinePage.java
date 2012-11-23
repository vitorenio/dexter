package br.furb.extbuilder.ui.outline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.AbstractAction;
import org.eclipse.jface.action.Action;
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

		menuMgr.add(new Action() {


			@Override
			public void runWithEvent(Event event) {
				TreeSelection ts = (TreeSelection) getSelection();				
				List<Component> list = ts.toList();				
				for(Component cmp:list){					
					System.out.println("it_"+ cmp.getName());
				}
				System.out.println("done!");
			}


			@Override
			public String getText() {
				return "Cobaia 1";
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

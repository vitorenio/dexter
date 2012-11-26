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
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.TreeItem;
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

		final TreeViewer tree = getTreeViewer();
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
				for (Component cmp : list) {
					System.out.println("it_" + cmp.getName());
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

		// DND
		/* drop */
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(tree.getTree(), operations);

		// Receive data in Text or File format
		final TextTransfer textTransfer = TextTransfer.getInstance();
		// final FileTransfer fileTransfer = FileTransfer.getInstance();
		Transfer[] types = new Transfer[] { /* fileTransfer, */textTransfer };
		target.setTransfer(types);

		target.addDropListener(new DropTargetListener() {

			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				/*
				 * for (int i = 0; i < event.dataTypes.length; i++) { if
				 * (fileTransfer.isSupportedType(event.dataTypes[i])) {
				 * event.currentDataType = event.dataTypes[i]; // files should
				 * only be copied if (event.detail != DND.DROP_COPY) {
				 * event.detail = DND.DROP_NONE; } break; } }
				 */
			}

			public void dragOver(DropTargetEvent event) {
				/*
				 * event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				 * if (textTransfer.isSupportedType(event.currentDataType)) { //
				 * NOTE: on unsupported platforms this will return null Object o
				 * = textTransfer.nativeToJava(event.currentDataType); String t
				 * = (String) o; if (t != null) System.out.println(t); }
				 */
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				/*
				 * if (fileTransfer.isSupportedType(event.currentDataType)) { if
				 * (event.detail != DND.DROP_COPY) { event.detail =
				 * DND.DROP_NONE; } }
				 */
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (textTransfer.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;

					TreeItem item = new TreeItem((TreeItem)event.item, SWT.NONE);
					item.setText(text);
				}
				/*
				 * if (fileTransfer.isSupportedType(event.currentDataType)) {
				 * String[] files = (String[]) event.data; for (int i = 0; i <
				 * files.length; i++) { TableItem item = new
				 * TableItem(dropTable, SWT.NONE); item.setText(files[i]); } }
				 */
			}
		});

	}

}

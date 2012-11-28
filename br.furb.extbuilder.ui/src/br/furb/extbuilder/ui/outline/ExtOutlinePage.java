package br.furb.extbuilder.ui.outline;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import br.furb.extbuilder.core.Conversible;
import br.furb.extbuilder.ui.component.Button;
import br.furb.extbuilder.ui.component.Component;
import br.furb.extbuilder.ui.component.Panel;
import br.furb.extbuilder.ui.component.Text;

public class ExtOutlinePage extends ContentOutlinePage {

	private Panel root;

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
				   GsonBuilder builder = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
				   builder.registerTypeAdapter(Component.class, new ComponentAdapter().nullSafe());

				   Gson gson = builder.create();
				   
		        if (root instanceof Component){
		        	System.out.println(gson.toJson(root));
		        }
		        
		        
			}

			@Override
			public String getText() {
				return "Refresh";
			}

		});
		final Menu menu = menuMgr.createContextMenu(parent);

		tree.getTree().setMenu(menu);
		// Be sure to register it so that other plug-ins can add actions.
		getSite().registerContextMenu(
				"org.eclipse.ui.examples.readmetool.outline", menuMgr, tree); //$NON-NLS-1$

		root = new Panel();
		root.setName("root");

		Panel child1 = new Panel();
		child1.setName("child1");

		root.addChild(child1);

		Panel child2 = new Panel();
		child2.setName("child2");
		child1.addChild(child2);

		tree.setInput(root);
		
		getSite().getPage().getActivePart().addPropertyListener(new IPropertyListener() {
			
			@Override
			public void propertyChanged(Object source, int propId) {
				System.out.println("");
				
			}
		});
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

					Component newCmp;
					if ("Button".equals(text)){
						newCmp = new Button();
					}else if ("Text".equals(text)){
						newCmp = new Text();
					}else if ("Panel".equals(text)){
						newCmp = new Panel();
					}else{
						return;
					}
					
					newCmp.setName("new_comp");
					
					TreeItem parentItem = (TreeItem) event.item;
					
					//se nao soltou o controle sobre nenhum componente, adiciona no painel principal
					if (parentItem == null){
						root.addChild(newCmp);
					}else{
						Component parentObj = (Component)parentItem.getData();
						
						parentObj.addChild(newCmp);
					}
					tree.refresh();
					
					
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
	
	private class ComponentAdapter extends TypeAdapter<Component> {
	     public Component read(JsonReader reader) throws IOException {
	         if (reader.peek() == JsonToken.NULL) {
	           reader.nextNull();
	           return null;
	         }
	         return null;
	       }
	       public void write(JsonWriter writer, Component value) throws IOException {
	         if (value == null) {
	           writer.nullValue();
	           return;
	         }
	         if (value instanceof Panel){
	        	 writer.value(value.getName());
	         }
	         
	       }
	     }

}

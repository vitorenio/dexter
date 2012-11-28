package br.furb.extbuilder.ui.outline;

import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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

import br.furb.extbuilder.ui.component.Button;
import br.furb.extbuilder.ui.component.Component;
import br.furb.extbuilder.ui.component.Panel;
import br.furb.extbuilder.ui.component.Text;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Classe responsável por exibir no outline do editor a arvore de componentes da tela. 
 * 
 * @author vitor
 *
 */
public class ExtOutlinePage extends ContentOutlinePage {

	/**
	 * Representa o objeto raiz da interface. Presume-se que toda interface esteja construida dentro de um elemento root.
	 */
	private Panel root;

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		final TreeViewer tree = getTreeViewer();
		tree.addSelectionChangedListener(this);
		tree.setContentProvider(new ExtOutlineContentProvider());
		tree.setLabelProvider(new ExtOutlineLabelProvider());
		
		// Configura o menu de contexto
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$

		menuMgr.add(new Separator()); //$NON-NLS-1$

		menuMgr.add(new Action() {

			@Override
			public void runWithEvent(Event event) {
				/** TODO: mock tosco apenas para ter uma idéia de como seria a renderizaçao dos componentes. */
				
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
		
		// registra o menu
		getSite().registerContextMenu(
				"org.eclipse.ui.examples.readmetool.outline", menuMgr, tree); //$NON-NLS-1$

		/**TODO: mock de workaround para o problema de integraçao dos properties com o outline*/
		root = new Panel();
		root.setName("root");

		Panel child1 = new Panel();
		child1.setName("child1");

		root.addChild(child1);

		Panel child2 = new Panel();
		child2.setName("child2");
		child1.addChild(child2);
		/***/
			
		tree.setInput(root);
		
		getSite().getPage().getActivePart().addPropertyListener(new IPropertyListener() {
			
			@Override
			public void propertyChanged(Object source, int propId) {
				//System.out.println("");
				
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

			@Override
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				
			}

			@Override
			public void dragOver(DropTargetEvent event) {
				/*nothing to do here*/
			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
			}
			
			@Override
			public void dragLeave(DropTargetEvent event) {
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
			}

			@Override
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

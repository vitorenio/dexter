package br.furb.extbuilder.ui.outline;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import br.furb.extbuilder.ui.component.Component;
import br.furb.extbuilder.ui.component.Panel;

/**
 * Classe responsável por fornecer o conteudo do modelo para o contentprovider.<br>
 * É nesta classe que se tem conhecimento da arvore real de componentes
 *  
 * @author vitor
 *
 */
public class ExtOutlineContentProvider implements ITreeContentProvider, IPropertyChangeListener {

	
    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Panel){
            return ((Panel) parentElement).getChildren().toArray();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Component){
            return ((Component) element).getParent();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {      
        Object[] children = getChildren(element);
        return children != null && children.length > 0;
    }

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("uebaa!!");
	}
    
}

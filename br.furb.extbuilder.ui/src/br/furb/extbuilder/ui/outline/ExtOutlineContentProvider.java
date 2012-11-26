package br.furb.extbuilder.ui.outline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import br.furb.extbuilder.ui.component.Panel;


public class ExtOutlineContentProvider implements ITreeContentProvider {

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub
        
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
        if (element instanceof Panel){
            return ((Panel) element).getParent();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {      
        Object[] children = getChildren(element);
        return children != null && children.length > 0;
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("c:/temp/to_parse.txt"));
            String line;
            while((line = reader.readLine()) != null){
                String prop = line.split(" ")[0];
                System.out.println(prop + "(\"" + line.replaceFirst(prop + " ", "") + "\", false),");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    
}

package br.furb.extbuilder.ui.outline;

import org.eclipse.jface.viewers.LabelProvider;

import br.furb.extbuilder.ui.component.Panel;


public class ExtOutlineLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof Panel){
            return ((Panel) element).getName();
        }
        return "elemento desconhecido";
    }



}

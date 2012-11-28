package br.furb.extbuilder.ui.outline;

import org.eclipse.jface.viewers.LabelProvider;

import br.furb.extbuilder.ui.component.Button;
import br.furb.extbuilder.ui.component.Panel;
import br.furb.extbuilder.ui.component.Text;

/**
 * Classe responsável por fornecer labels para o outline
 * @author vitor
 *
 */
public class ExtOutlineLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof Panel){
            return ((Panel) element).getName();
        }else if (element instanceof Button){
            return ((Button) element).getName();
        }else if (element instanceof Text){
            return ((Text) element).getName();
        }
        return "elemento desconhecido";
    }



}

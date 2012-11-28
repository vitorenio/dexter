package br.furb.extbuilder.core.visitors;

public interface PanelElement {

	public void accept(PanelElementVisitor visitor);
	
}

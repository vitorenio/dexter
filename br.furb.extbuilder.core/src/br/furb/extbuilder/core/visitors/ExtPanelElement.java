package br.furb.extbuilder.core.visitors;

public class ExtPanelElement implements PanelElement {

	@Override
	public void accept(PanelElementVisitor visitor) {
		visitor.visit(this);
	}

}

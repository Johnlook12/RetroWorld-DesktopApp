package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JTable;

import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class OpenPedidoCerradoDetailAction extends OpenPedidoDetailAction{
	
	public OpenPedidoCerradoDetailAction(PedidoSearchView view) {
		super(view);
	}
	
	public OpenPedidoCerradoDetailAction(PedidoSearchView view, String name) {
		super(view, name);
	}
	
	public OpenPedidoCerradoDetailAction(PedidoSearchView view, String name, Icon icon) {
		 super(view, name, icon);
	}
	
	@Override
	public JTable getTable() {
		return super.view.getCerradosTableResults();
	}
	
}

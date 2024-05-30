package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.dialog.CreateEmpleadoDialog;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoSearchView;

public class OpenCreateEmpleadoAction extends AbstractAction{
	
	public OpenCreateEmpleadoAction() {
	
	}
	
	public OpenCreateEmpleadoAction(String name) {
		super(name);
	}
	
	public OpenCreateEmpleadoAction(String name, Icon icon) {
		super(name,icon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CreateEmpleadoDialog dialog = new CreateEmpleadoDialog();
		dialog.setVisible(true);
	}

}

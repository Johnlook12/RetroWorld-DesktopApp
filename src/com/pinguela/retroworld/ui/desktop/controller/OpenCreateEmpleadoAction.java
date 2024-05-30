package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.dialog.CreateEmpleadoDialog;

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
		dialog.addPropertyChangeListener(new PropertyChangeListener() {	
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equalsIgnoreCase(CreateEmpleadoDialog.CLOSE_DIALOG_PROPERTY)) {
					dialog.dispose();
				}
			}
		});
		dialog.setVisible(true);
	}

}

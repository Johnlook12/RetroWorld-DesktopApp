package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;

public class LogOutAction extends AbstractAction{

	public LogOutAction() {
		
	}
	
	public LogOutAction(String name) {
		super(name);
	}
	
	public LogOutAction(String name, Icon icon) {
		super(name, icon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		RetroWorldWindow window = RetroWorldWindow.getInstance();
		window.setEmpleadoAutenticado(null);
		window.setVisible(false);
		window.showLoginView();
	}

}

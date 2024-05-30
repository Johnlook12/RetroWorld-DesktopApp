package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.dialog.RWDialog;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.LoginView;

public class OpenLoginAction extends BaseAction{

	public OpenLoginAction() {
	}
	
	public OpenLoginAction(String name) {
		super(name);
	}
	
	public OpenLoginAction(String name, Icon icon) {
		super(name, icon);
	}
	
	@Override
	public void doAction() {
		LoginView loginView = new LoginView();
		RWDialog loginDialog = new RWDialog();
		SwingUtils.centerOnScreen(loginDialog);
		loginView.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (LoginView.EMPLEADO_PROPERTY.equalsIgnoreCase(evt.getPropertyName())) {
					loginDialog.setVisible(false);
				}
			}
		});
		RetroWorldWindow window = RetroWorldWindow.getInstance();
		loginDialog.getContentPane().add(loginView);
		window.setLoginDialog(loginDialog);
		window.showLoginView();
		
		
	}

}

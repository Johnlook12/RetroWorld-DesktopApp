package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.dialog.ChangePasswordDialog;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;

public class OpenChangePasswordAction extends BaseAction{

	public OpenChangePasswordAction() {
	}
	
	public OpenChangePasswordAction(String name) {
		super(name);
	}
	
	public OpenChangePasswordAction(String name, Icon icon) {
		super(name, icon);
	}
	
	@Override
	public void doAction() {
		ChangePasswordDialog passwordDialog = new ChangePasswordDialog();
		passwordDialog.addPropertyChangeListener(new PropertyChangeListener() {	
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equalsIgnoreCase(passwordDialog.CHANGE_PASSWORD_PROPERTY)) {
					passwordDialog.dispose();
				}
			}
		});
		SwingUtils.centerOnParent(passwordDialog, true);
		passwordDialog.setVisible(true);
	}

}

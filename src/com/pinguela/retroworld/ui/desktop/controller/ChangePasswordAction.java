package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.ChangePasswordDialog;

public class ChangePasswordAction extends BaseAction{
	
	private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();
	private static Logger logger = LogManager.getLogger(ChangePasswordAction.class);
	private ChangePasswordDialog dialog;
	private EmpleadoService empleadoService;
	
	public ChangePasswordAction(ChangePasswordDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public ChangePasswordAction(ChangePasswordDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public ChangePasswordAction(ChangePasswordDialog dialog, String name, Icon icon) {
		super(name, icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			String currentPassword = dialog.getCurrentPassword();
			String newPassword = dialog.getNewPassword();
			String repeatNewPassword = dialog.getRepeatNewPassword();
			Empleado currentEmpleado = dialog.getCurrentEmpleado();
			if(PASSWORD_ENCRYPTOR.checkPassword(currentPassword, currentEmpleado.getPassword()) 
					&& (newPassword.equalsIgnoreCase(repeatNewPassword))) {
				logger.info("Cambiando contraseña para el empleado con id "+currentEmpleado.getId());
				empleadoService.updatePassword(newPassword, currentEmpleado.getId());
				JOptionPane.showMessageDialog(dialog, "Contraseña actualizada correctamente");
				currentEmpleado.setPassword(newPassword);
				dialog.notifyCloseDialog();
			} else if(!newPassword.equalsIgnoreCase(repeatNewPassword)) {
				JOptionPane.showMessageDialog(dialog, "Las contraseñas no coinciden", "Error al cambiar contraseña", JOptionPane.ERROR_MESSAGE);
				logger.error("error al cambiar la contraseña para el empleado con id "+currentEmpleado.getId());
			} else {
				JOptionPane.showMessageDialog(dialog, "Contraseña incorrecta", "Error al cambiar contraseña", JOptionPane.ERROR_MESSAGE);
				logger.error("error al cambiar la contraseña para el empleado con id "+currentEmpleado.getId());
			}
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}

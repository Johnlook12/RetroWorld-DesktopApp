package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.ChangePasswordAction;

public class ChangePasswordDialog extends RWDialog {
	private static Logger logger = LogManager.getLogger(ChangePasswordDialog.class);
	public final String CHANGE_PASSWORD_PROPERTY = "cancelChangePassword";
	
	private final JPanel contentPanel = new JPanel();
	private Empleado currentEmpleado;
	
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField repeatNewPasswordField;
	
	
	public static void main(String[] args) {
		try {
			ChangePasswordDialog dialog = new ChangePasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void postInitialize() {
		RetroWorldWindow window = RetroWorldWindow.getInstance();
		currentEmpleado = window.getEmpleadoAutenticado();
	}
	
	public ChangePasswordDialog() {
		setBounds(100, 100, 608, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 41, 0, 95, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel actualPasswordLbl = new JLabel("Contraseña actual:");
			GridBagConstraints gbc_actualPasswordLbl = new GridBagConstraints();
			gbc_actualPasswordLbl.anchor = GridBagConstraints.EAST;
			gbc_actualPasswordLbl.insets = new Insets(0, 0, 5, 5);
			gbc_actualPasswordLbl.gridx = 6;
			gbc_actualPasswordLbl.gridy = 2;
			contentPanel.add(actualPasswordLbl, gbc_actualPasswordLbl);
		}
		{
			currentPasswordField = new JPasswordField();
			GridBagConstraints gbc_actualPasswordField = new GridBagConstraints();
			gbc_actualPasswordField.insets = new Insets(0, 0, 5, 5);
			gbc_actualPasswordField.fill = GridBagConstraints.BOTH;
			gbc_actualPasswordField.gridx = 7;
			gbc_actualPasswordField.gridy = 2;
			contentPanel.add(currentPasswordField, gbc_actualPasswordField);
		}
		{
			JLabel newPasswordLbl = new JLabel("Nueva contraseña:");
			GridBagConstraints gbc_newPasswordLbl = new GridBagConstraints();
			gbc_newPasswordLbl.anchor = GridBagConstraints.EAST;
			gbc_newPasswordLbl.insets = new Insets(0, 0, 5, 5);
			gbc_newPasswordLbl.gridx = 6;
			gbc_newPasswordLbl.gridy = 4;
			contentPanel.add(newPasswordLbl, gbc_newPasswordLbl);
		}
		{
			newPasswordField = new JPasswordField();
			GridBagConstraints gbc_newPasswordField = new GridBagConstraints();
			gbc_newPasswordField.insets = new Insets(0, 0, 5, 5);
			gbc_newPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_newPasswordField.gridx = 7;
			gbc_newPasswordField.gridy = 4;
			contentPanel.add(newPasswordField, gbc_newPasswordField);
		}
		{
			JLabel repeatNewPasswordLbl = new JLabel("Repetir contraseña:");
			GridBagConstraints gbc_repeatNewPasswordLbl = new GridBagConstraints();
			gbc_repeatNewPasswordLbl.anchor = GridBagConstraints.EAST;
			gbc_repeatNewPasswordLbl.insets = new Insets(0, 0, 5, 5);
			gbc_repeatNewPasswordLbl.gridx = 6;
			gbc_repeatNewPasswordLbl.gridy = 6;
			contentPanel.add(repeatNewPasswordLbl, gbc_repeatNewPasswordLbl);
		}
		{
			repeatNewPasswordField = new JPasswordField();
			GridBagConstraints gbc_repeatNewPasswordField = new GridBagConstraints();
			gbc_repeatNewPasswordField.insets = new Insets(0, 0, 5, 5);
			gbc_repeatNewPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_repeatNewPasswordField.gridx = 7;
			gbc_repeatNewPasswordField.gridy = 6;
			contentPanel.add(repeatNewPasswordField, gbc_repeatNewPasswordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton();
				okButton.setAction(new ChangePasswordAction(this, "OK"));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						notifyCloseDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		postInitialize();
	}

	public String getCurrentPassword() {
		return new String(currentPasswordField.getPassword());
	}
	
	public String getNewPassword() {
		return new String(newPasswordField.getPassword());
	}
	
	public String getRepeatNewPassword() {
		return new String(repeatNewPasswordField.getPassword());
	}
	
	public Empleado getCurrentEmpleado() {
		return this.currentEmpleado;
	}
	
	public void notifyCloseDialog() {
		firePropertyChange(CHANGE_PASSWORD_PROPERTY, null, currentEmpleado);
	}
}

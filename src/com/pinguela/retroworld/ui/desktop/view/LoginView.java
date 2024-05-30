package com.pinguela.retroworld.ui.desktop.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.controller.LoginAction;
import javax.swing.JSeparator;

public class LoginView extends JPanel{
	private Logger logger = LogManager.getLogger(LoginView.class);
	public static final String EMPLEADO_PROPERTY = "empleado";
	private EmpleadoService empleadoService=null;
	private Empleado empleadoAutenticado = null;
	
	private JPanel loginPane;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	
	
	public void postInitialize() {
		initServices();
		loginPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
		Dimension expectedDimension = new Dimension(300, 150);
		loginPane.setPreferredSize(expectedDimension);
	}
	
	public LoginView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 49, 0, 231, 386, 0, 48, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 251, 170, 65, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JSeparator northSeparator = new JSeparator();
		GridBagConstraints gbc_northSeparator = new GridBagConstraints();
		gbc_northSeparator.gridwidth = 2;
		gbc_northSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_northSeparator.gridx = 3;
		gbc_northSeparator.gridy = 1;
		add(northSeparator, gbc_northSeparator);
		
		JSeparator westSeparator = new JSeparator();
		GridBagConstraints gbc_westSeparator = new GridBagConstraints();
		gbc_westSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_westSeparator.gridx = 0;
		gbc_westSeparator.gridy = 2;
		add(westSeparator, gbc_westSeparator);
		
		loginPane = new JPanel();
		GridBagConstraints gbc_loginPane = new GridBagConstraints();
		gbc_loginPane.fill = GridBagConstraints.BOTH;
		gbc_loginPane.gridheight = 3;
		gbc_loginPane.gridwidth = 2;
		gbc_loginPane.insets = new Insets(0, 0, 5, 5);
		gbc_loginPane.gridx = 3;
		gbc_loginPane.gridy = 2;
		add(loginPane, gbc_loginPane);
		GridBagLayout gbl_loginPane = new GridBagLayout();
		gbl_loginPane.columnWidths = new int[]{0, 0, 122, 59, 88, 87, 55, 53, 90, 0};
		gbl_loginPane.rowHeights = new int[]{0, 0, 0, 0, 68, 0, 75, 0, 59, 0, 73, 0, 0};
		gbl_loginPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_loginPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loginPane.setLayout(gbl_loginPane);
		
		JLabel loginIcon = new JLabel("");
		loginIcon.setIcon(new ImageIcon(LoginView.class.getResource("/icons/logo2.png")));
		GridBagConstraints gbc_loginIcon = new GridBagConstraints();
		gbc_loginIcon.gridwidth = 2;
		gbc_loginIcon.gridheight = 2;
		gbc_loginIcon.insets = new Insets(0, 0, 5, 5);
		gbc_loginIcon.gridx = 4;
		gbc_loginIcon.gridy = 1;
		loginPane.add(loginIcon, gbc_loginIcon);
		
		JLabel loginMessage = new JLabel("Iniciar Sesión");
		loginMessage.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_loginMessage = new GridBagConstraints();
		gbc_loginMessage.gridwidth = 2;
		gbc_loginMessage.insets = new Insets(0, 0, 5, 5);
		gbc_loginMessage.gridx = 4;
		gbc_loginMessage.gridy = 3;
		loginPane.add(loginMessage, gbc_loginMessage);
		
		JLabel correoLbl = new JLabel("Email:");
		correoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_correoLbl = new GridBagConstraints();
		gbc_correoLbl.anchor = GridBagConstraints.EAST;
		gbc_correoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_correoLbl.gridx = 2;
		gbc_correoLbl.gridy = 5;
		loginPane.add(correoLbl, gbc_correoLbl);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.gridwidth = 5;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 3;
		gbc_emailTextField.gridy = 5;
		loginPane.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Contraseña:");
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 2;
		gbc_passwordLbl.gridy = 7;
		loginPane.add(passwordLbl, gbc_passwordLbl);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 5;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 7;
		loginPane.add(passwordField, gbc_passwordField);
		GridBagConstraints gbc_loginBtn = new GridBagConstraints();
		gbc_loginBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginBtn.gridwidth = 2;
		gbc_loginBtn.insets = new Insets(0, 0, 5, 5);
		gbc_loginBtn.gridx = 4;
		gbc_loginBtn.gridy = 9;
		
		JButton loginBtn = new JButton("Ingresar");
		loginBtn.addActionListener(new LoginAction(this));
		loginPane.add(loginBtn, gbc_loginBtn);
		
		JSeparator eastSeparator = new JSeparator();
		GridBagConstraints gbc_eastSeparator = new GridBagConstraints();
		gbc_eastSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_eastSeparator.gridx = 7;
		gbc_eastSeparator.gridy = 2;
		add(eastSeparator, gbc_eastSeparator);
		
		JSeparator southSeparator = new JSeparator();
		GridBagConstraints gbc_southSeparator = new GridBagConstraints();
		gbc_southSeparator.gridwidth = 2;
		gbc_southSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_southSeparator.gridx = 3;
		gbc_southSeparator.gridy = 5;
		add(southSeparator, gbc_southSeparator);
		
		postInitialize();
	}
	
	public void setEmpleadoAutenticado(Empleado e) {
		this.empleadoAutenticado = e;
		firePropertyChange(LoginView.EMPLEADO_PROPERTY, null, e);
	}
	
	public String getEmpleadoEmail() {
		return emailTextField.getText();
	}
	
	public String getEmpleadoPassword() {
		return new String(passwordField.getPassword());
	}
	
	private void initServices() {
		empleadoService= new EmpleadoServiceImpl();
	}
}

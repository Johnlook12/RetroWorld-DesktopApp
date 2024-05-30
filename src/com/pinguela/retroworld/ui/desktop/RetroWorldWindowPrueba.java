package com.pinguela.retroworld.ui.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.service.UsuarioService;
import com.pinguela.retroworld.service.impl.UsuarioServiceImpl;
import javax.swing.BoxLayout;

public class RetroWorldWindowPrueba {
	
	private JTextArea holaTxtArea = null; 
	private JFrame frame;
	private UsuarioService usuarioService= null;
	private JPanel loginPanel;
	private JPanel northPanel;
	private JLabel appIconLbl;
	private JLabel loginBienvenidaLbl;
	private JPanel centerPanel;
	private JPanel loginFormPanel;
	private JLabel loginMsgLbl;
	private JLabel usuarioLbl;
	private JTextField textField;
	private JLabel passwordLbl;
	private JPasswordField passwordField;
	private JPanel loginButtonPanel;
	private JButton loginButton;
	private JButton cancelBtn;
	private JPanel searchPanel;
	private JPanel searchFormPanel;
	private JPanel searchResultsPanel;
	private JLabel videojuegoNombreLbl;
	private JTextField textField_1;
	private JTextArea resultsTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetroWorldWindowPrueba window = new RetroWorldWindowPrueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public RetroWorldWindowPrueba() {
		initServices();
		initialize();
	}
	
	private void initServices() {
		usuarioService = new UsuarioServiceImpl();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 324);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, BorderLayout.NORTH);
		loginPanel.setLayout(new BorderLayout(0, 0));
		
		northPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		flowLayout.setVgap(-5);
		loginPanel.add(northPanel, BorderLayout.NORTH);
		
		appIconLbl = new JLabel("");
		appIconLbl.setIcon(new ImageIcon(RetroWorldWindowPrueba.class.getResource("/icons/logo2.png")));
		northPanel.add(appIconLbl);
		
		loginBienvenidaLbl = new JLabel("Bienvenido a RetroWorld!");
		northPanel.add(loginBienvenidaLbl);
		
		centerPanel = new JPanel();
		loginPanel.add(centerPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		loginFormPanel = new JPanel();
		centerPanel.add(loginFormPanel, BorderLayout.NORTH);
		GridBagLayout gbl_loginFormPanel = new GridBagLayout();
		gbl_loginFormPanel.columnWidths = new int[]{0, 0, 0};
		gbl_loginFormPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_loginFormPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_loginFormPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		loginFormPanel.setLayout(gbl_loginFormPanel);
		
		loginMsgLbl = new JLabel("");
		GridBagConstraints gbc_loginMsgLbl = new GridBagConstraints();
		gbc_loginMsgLbl.anchor = GridBagConstraints.WEST;
		gbc_loginMsgLbl.gridwidth = 2;
		gbc_loginMsgLbl.insets = new Insets(0, 0, 5, 0);
		gbc_loginMsgLbl.gridx = 0;
		gbc_loginMsgLbl.gridy = 0;
		loginFormPanel.add(loginMsgLbl, gbc_loginMsgLbl);
		
		usuarioLbl = new JLabel("Correo electrónico:");
		GridBagConstraints gbc_usuarioLbl = new GridBagConstraints();
		gbc_usuarioLbl.anchor = GridBagConstraints.EAST;
		gbc_usuarioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioLbl.gridx = 0;
		gbc_usuarioLbl.gridy = 1;
		loginFormPanel.add(usuarioLbl, gbc_usuarioLbl);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		loginFormPanel.add(textField, gbc_textField);
		
		passwordLbl = new JLabel("Contraseña:");
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 0;
		gbc_passwordLbl.gridy = 2;
		loginFormPanel.add(passwordLbl, gbc_passwordLbl);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		loginFormPanel.add(passwordField, gbc_passwordField);
		
		loginButtonPanel = new JPanel();
		centerPanel.add(loginButtonPanel, BorderLayout.SOUTH);
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		loginButton = new JButton("Entrar");
		loginButtonPanel.add(loginButton);
		
		cancelBtn = new JButton("Cancelar");
		loginButtonPanel.add(cancelBtn);
		
		searchPanel = new JPanel();
		frame.getContentPane().add(searchPanel, BorderLayout.CENTER);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		searchFormPanel = new JPanel();
		searchPanel.add(searchFormPanel, BorderLayout.NORTH);
		searchFormPanel.setLayout(new BoxLayout(searchFormPanel, BoxLayout.X_AXIS));
		
		videojuegoNombreLbl = new JLabel("Nombre:");
		searchFormPanel.add(videojuegoNombreLbl);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		searchFormPanel.add(textField_1);
		
		searchResultsPanel = new JPanel();
		searchPanel.add(searchResultsPanel, BorderLayout.SOUTH);
		searchResultsPanel.setLayout(new BoxLayout(searchResultsPanel, BoxLayout.X_AXIS));
		
		resultsTextArea = new JTextArea();
		searchResultsPanel.add(resultsTextArea);
	}

}

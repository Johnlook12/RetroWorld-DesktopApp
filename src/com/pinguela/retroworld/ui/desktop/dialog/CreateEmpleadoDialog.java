package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.ui.desktop.controller.CreateEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;

public class CreateEmpleadoDialog extends RWDialog {

	private Empleado empleado = null;
	private Direccion direccion = null;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField apellido1TextField;
	private JTextField apellido2TextField;
	private JTextField emailTextField;
	private JTextField telefonoTextField;
	private JTextField documentoTextField;
	private JTextField tipoViaTextField;
	private JTextField nombreViaTextField;
	private JTextField dirViaTextField;
	private JTextField localidadTextField;
	private JTextField pisoTextField;
	private JTextField letraTextField;
	private JTextField nombreTextField;
	private JSpinner codigoPostalSpinner;
	private JScrollPane dataScrollPane;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private List<JTextComponent> textFields;
	
	public static void main(String[] args) {
		try {
			CreateEmpleadoDialog dialog = new CreateEmpleadoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public CreateEmpleadoDialog() {
		setBounds(100, 100, 529, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{155, 0, 0, 96, 0};
		gbl_contentPanel.rowHeights = new int[]{44, 44, 26, 0, 0, 0, 0, 0, 16, 0, 26, 0, 0, 0, 0, 0, 0, 23, 0, 22, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		JLabel titleLbl = new JLabel("Registrar empleado");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_titleLbl = new GridBagConstraints();
		gbc_titleLbl.gridwidth = 2;
		gbc_titleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_titleLbl.gridx = 1;
		gbc_titleLbl.gridy = 0;
		contentPanel.add(titleLbl, gbc_titleLbl);


		JLabel informacionLbl = new JLabel("Información personal");
		informacionLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_informacionLbl = new GridBagConstraints();
		gbc_informacionLbl.gridwidth = 2;
		gbc_informacionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_informacionLbl.gridx = 1;
		gbc_informacionLbl.gridy = 1;
		contentPanel.add(informacionLbl, gbc_informacionLbl);


		JLabel nombreLbl = new JLabel("Nombre:");
		GridBagConstraints gbc_nombreLbl = new GridBagConstraints();
		gbc_nombreLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLbl.gridx = 1;
		gbc_nombreLbl.gridy = 2;
		contentPanel.add(nombreLbl, gbc_nombreLbl);


		nombreTextField = new JTextField();
		GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
		gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTextField.gridx = 2;
		gbc_nombreTextField.gridy = 2;
		contentPanel.add(nombreTextField, gbc_nombreTextField);
		nombreTextField.setColumns(10);


		JLabel apellido1Lbl = new JLabel("Primer apellido:");
		GridBagConstraints gbc_apellido1Lbl = new GridBagConstraints();
		gbc_apellido1Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Lbl.gridx = 1;
		gbc_apellido1Lbl.gridy = 3;
		contentPanel.add(apellido1Lbl, gbc_apellido1Lbl);


		apellido1TextField = new JTextField();
		GridBagConstraints gbc_apellido1TextField = new GridBagConstraints();
		gbc_apellido1TextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TextField.gridx = 2;
		gbc_apellido1TextField.gridy = 3;
		contentPanel.add(apellido1TextField, gbc_apellido1TextField);
		apellido1TextField.setColumns(10);


		JLabel apellido2Lbl = new JLabel("Segundo apellido:");
		GridBagConstraints gbc_apellido2Lbl = new GridBagConstraints();
		gbc_apellido2Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Lbl.gridx = 1;
		gbc_apellido2Lbl.gridy = 4;
		contentPanel.add(apellido2Lbl, gbc_apellido2Lbl);


		apellido2TextField = new JTextField();
		GridBagConstraints gbc_apellido2TextField = new GridBagConstraints();
		gbc_apellido2TextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TextField.gridx = 2;
		gbc_apellido2TextField.gridy = 4;
		contentPanel.add(apellido2TextField, gbc_apellido2TextField);
		apellido2TextField.setColumns(10);


		JLabel emailLbl = new JLabel("Email:");
		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 1;
		gbc_emailLbl.gridy = 5;
		contentPanel.add(emailLbl, gbc_emailLbl);


		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 5;
		contentPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);


		JLabel telefonoLbl = new JLabel("Teléfono:");
		GridBagConstraints gbc_telefonoLbl = new GridBagConstraints();
		gbc_telefonoLbl.anchor = GridBagConstraints.EAST;
		gbc_telefonoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLbl.gridx = 1;
		gbc_telefonoLbl.gridy = 6;
		contentPanel.add(telefonoLbl, gbc_telefonoLbl);


		telefonoTextField = new JTextField();
		GridBagConstraints gbc_telefonoTextField = new GridBagConstraints();
		gbc_telefonoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTextField.gridx = 2;
		gbc_telefonoTextField.gridy = 6;
		contentPanel.add(telefonoTextField, gbc_telefonoTextField);
		telefonoTextField.setColumns(10);


		JLabel documentoLbl = new JLabel("DNI/NIE:");
		GridBagConstraints gbc_documentoLbl = new GridBagConstraints();
		gbc_documentoLbl.anchor = GridBagConstraints.EAST;
		gbc_documentoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_documentoLbl.gridx = 1;
		gbc_documentoLbl.gridy = 7;
		contentPanel.add(documentoLbl, gbc_documentoLbl);


		documentoTextField = new JTextField();
		GridBagConstraints gbc_documentoTextField = new GridBagConstraints();
		gbc_documentoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_documentoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentoTextField.gridx = 2;
		gbc_documentoTextField.gridy = 7;
		contentPanel.add(documentoTextField, gbc_documentoTextField);
		documentoTextField.setColumns(10);


		JSeparator informacionSeparator = new JSeparator();
		informacionSeparator.setBackground(new Color(128, 128, 128));
		informacionSeparator.setForeground(new Color(0, 0, 0));
		informacionSeparator.setPreferredSize(new Dimension(200, 10));
		GridBagConstraints gbc_informacionSeparator = new GridBagConstraints();
		gbc_informacionSeparator.fill = GridBagConstraints.HORIZONTAL;
		gbc_informacionSeparator.gridwidth = 4;
		gbc_informacionSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_informacionSeparator.gridx = 0;
		gbc_informacionSeparator.gridy = 8;
		contentPanel.add(informacionSeparator, gbc_informacionSeparator);


		JLabel direccionLbl = new JLabel("Dirección");
		direccionLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_direccionLbl = new GridBagConstraints();
		gbc_direccionLbl.gridwidth = 2;
		gbc_direccionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLbl.gridx = 1;
		gbc_direccionLbl.gridy = 9;
		contentPanel.add(direccionLbl, gbc_direccionLbl);


		JLabel tipoViaLbl = new JLabel("Tipo vía:");
		GridBagConstraints gbc_tipoViaLbl = new GridBagConstraints();
		gbc_tipoViaLbl.anchor = GridBagConstraints.EAST;
		gbc_tipoViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaLbl.gridx = 1;
		gbc_tipoViaLbl.gridy = 10;
		contentPanel.add(tipoViaLbl, gbc_tipoViaLbl);


		tipoViaTextField = new JTextField();
		GridBagConstraints gbc_tipoViaTextField = new GridBagConstraints();
		gbc_tipoViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoViaTextField.gridx = 2;
		gbc_tipoViaTextField.gridy = 10;
		contentPanel.add(tipoViaTextField, gbc_tipoViaTextField);
		tipoViaTextField.setColumns(10);


		JLabel nombreViaLbl = new JLabel("Nombre vía:");
		GridBagConstraints gbc_nombreViaLbl = new GridBagConstraints();
		gbc_nombreViaLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaLbl.gridx = 1;
		gbc_nombreViaLbl.gridy = 11;
		contentPanel.add(nombreViaLbl, gbc_nombreViaLbl);

		nombreViaTextField = new JTextField();
		GridBagConstraints gbc_nombreViaTextField = new GridBagConstraints();
		gbc_nombreViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreViaTextField.gridx = 2;
		gbc_nombreViaTextField.gridy = 11;
		contentPanel.add(nombreViaTextField, gbc_nombreViaTextField);
		nombreViaTextField.setColumns(10);

		JLabel dirViaLbl = new JLabel("Número:");
		GridBagConstraints gbc_dirViaLbl = new GridBagConstraints();
		gbc_dirViaLbl.anchor = GridBagConstraints.EAST;
		gbc_dirViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaLbl.gridx = 1;
		gbc_dirViaLbl.gridy = 12;
		contentPanel.add(dirViaLbl, gbc_dirViaLbl);

		dirViaTextField = new JTextField();
		GridBagConstraints gbc_dirViaTextField = new GridBagConstraints();
		gbc_dirViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dirViaTextField.gridx = 2;
		gbc_dirViaTextField.gridy = 12;
		contentPanel.add(dirViaTextField, gbc_dirViaTextField);
		dirViaTextField.setColumns(10);

		JLabel pisoLbl = new JLabel("Piso:");
		GridBagConstraints gbc_pisoLbl = new GridBagConstraints();
		gbc_pisoLbl.anchor = GridBagConstraints.EAST;
		gbc_pisoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pisoLbl.gridx = 1;
		gbc_pisoLbl.gridy = 13;
		contentPanel.add(pisoLbl, gbc_pisoLbl);

		pisoTextField = new JTextField();
		GridBagConstraints gbc_pisoTextField = new GridBagConstraints();
		gbc_pisoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pisoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pisoTextField.gridx = 2;
		gbc_pisoTextField.gridy = 13;
		contentPanel.add(pisoTextField, gbc_pisoTextField);
		pisoTextField.setColumns(10);

		JLabel letraLbl = new JLabel("Letra:");
		GridBagConstraints gbc_letraLbl = new GridBagConstraints();
		gbc_letraLbl.anchor = GridBagConstraints.EAST;
		gbc_letraLbl.insets = new Insets(0, 0, 5, 5);
		gbc_letraLbl.gridx = 1;
		gbc_letraLbl.gridy = 14;
		contentPanel.add(letraLbl, gbc_letraLbl);

		letraTextField = new JTextField();
		GridBagConstraints gbc_letraTextField = new GridBagConstraints();
		gbc_letraTextField.insets = new Insets(0, 0, 5, 5);
		gbc_letraTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_letraTextField.gridx = 2;
		gbc_letraTextField.gridy = 14;
		contentPanel.add(letraTextField, gbc_letraTextField);
		letraTextField.setColumns(10);

		JLabel nombreLocalidadLbl = new JLabel("Localidad:");
		GridBagConstraints gbc_nombreLocalidadLbl = new GridBagConstraints();
		gbc_nombreLocalidadLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreLocalidadLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLocalidadLbl.gridx = 1;
		gbc_nombreLocalidadLbl.gridy = 15;
		contentPanel.add(nombreLocalidadLbl, gbc_nombreLocalidadLbl);

		localidadTextField = new JTextField();
		GridBagConstraints gbc_localidadTextField = new GridBagConstraints();
		gbc_localidadTextField.insets = new Insets(0, 0, 5, 5);
		gbc_localidadTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_localidadTextField.gridx = 2;
		gbc_localidadTextField.gridy = 15;
		contentPanel.add(localidadTextField, gbc_localidadTextField);
		localidadTextField.setColumns(10);

		JLabel codigoPostalLbl = new JLabel("Código Postal:");
		GridBagConstraints gbc_codigoPostalLbl = new GridBagConstraints();
		gbc_codigoPostalLbl.anchor = GridBagConstraints.EAST;
		gbc_codigoPostalLbl.insets = new Insets(0, 0, 5, 5);
		gbc_codigoPostalLbl.gridx = 1;
		gbc_codigoPostalLbl.gridy = 16;
		contentPanel.add(codigoPostalLbl, gbc_codigoPostalLbl);

		codigoPostalSpinner = new JSpinner();
		GridBagConstraints gbc_codigoPostalSpinner = new GridBagConstraints();
		gbc_codigoPostalSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_codigoPostalSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigoPostalSpinner.gridx = 2;
		gbc_codigoPostalSpinner.gridy = 16;
		contentPanel.add(codigoPostalSpinner, gbc_codigoPostalSpinner);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(UIManager.getBorder("Spinner.border"));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton registrarButton = new JButton("Registrar");
		registrarButton.addActionListener(new CreateEmpleadoAction(this));
		registrarButton.setActionCommand("OK");
		buttonPane.add(registrarButton);
		getRootPane().setDefaultButton(registrarButton);


		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(CLOSE_DIALOG_PROPERTY, null, null);
				
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		dataScrollPane = new JScrollPane();
		getContentPane().add(dataScrollPane, BorderLayout.CENTER);
		dataScrollPane.setViewportView(contentPanel);
		
		JSeparator direccionSeparator = new JSeparator();
		direccionSeparator.setPreferredSize(new Dimension(200, 10));
		direccionSeparator.setForeground(Color.BLACK);
		direccionSeparator.setBackground(Color.GRAY);
		GridBagConstraints gbc_direccionSeparator = new GridBagConstraints();
		gbc_direccionSeparator.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionSeparator.gridwidth = 4;
		gbc_direccionSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_direccionSeparator.gridx = 0;
		gbc_direccionSeparator.gridy = 17;
		contentPanel.add(direccionSeparator, gbc_direccionSeparator);
		
		JLabel passwordTitleLbl = new JLabel("Clave de acceso");
		passwordTitleLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_passwordTitleLbl = new GridBagConstraints();
		gbc_passwordTitleLbl.gridwidth = 2;
		gbc_passwordTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTitleLbl.gridx = 1;
		gbc_passwordTitleLbl.gridy = 18;
		contentPanel.add(passwordTitleLbl, gbc_passwordTitleLbl);
		
		JLabel passwordLbl = new JLabel("Contraseña:");
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 19;
		contentPanel.add(passwordLbl, gbc_passwordLbl);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 19;
		contentPanel.add(passwordField, gbc_passwordField);
		
		JLabel repeatPasswordLbl = new JLabel("Repetir contraseña:");
		GridBagConstraints gbc_repeatPasswordLbl = new GridBagConstraints();
		gbc_repeatPasswordLbl.anchor = GridBagConstraints.EAST;
		gbc_repeatPasswordLbl.insets = new Insets(0, 0, 0, 5);
		gbc_repeatPasswordLbl.gridx = 1;
		gbc_repeatPasswordLbl.gridy = 20;
		contentPanel.add(repeatPasswordLbl, gbc_repeatPasswordLbl);
		
		repeatPasswordField = new JPasswordField();
		GridBagConstraints gbc_repeatPasswordField = new GridBagConstraints();
		gbc_repeatPasswordField.insets = new Insets(0, 0, 0, 5);
		gbc_repeatPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordField.gridx = 2;
		gbc_repeatPasswordField.gridy = 20;
		contentPanel.add(repeatPasswordField, gbc_repeatPasswordField);
		
		postInitialize();

	}

	
	private void postInitialize() {
		textFields = new ArrayList<JTextComponent>();
		empleado = new Empleado();
		direccion = new Direccion();
		
		textFields.add(apellido1TextField);
		textFields.add(apellido2TextField);
		textFields.add(dirViaTextField);
		textFields.add(documentoTextField);
		textFields.add(emailTextField);
		textFields.add(letraTextField);
		textFields.add(localidadTextField);
		textFields.add(nombreTextField);
		textFields.add(nombreViaTextField);
		textFields.add(pisoTextField);
		textFields.add(telefonoTextField);
		textFields.add(tipoViaTextField);
		
	}
	
	public boolean validarTextFields() {
		for(JTextComponent field:textFields) {
			if(field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public Empleado getEmpleado() {
		empleado.setNombre(nombreTextField.getText());
		empleado.setApellido1(apellido1TextField.getText());
		empleado.setApellido2(SwingUtils.getTextFieldValueOrNull(apellido2TextField));
		empleado.setDocumentoIdentidad(documentoTextField.getText());
		empleado.setTelefono(telefonoTextField.getText());
		empleado.setEmail(emailTextField.getText());
		empleado.setPassword(new String(passwordField.getPassword()));
		empleado.setIdTipoEmpleado(Empleado.TIPO_EMPLEADO);
		
		direccion.setCodigoPostal(Long.valueOf((Integer)codigoPostalSpinner.getValue()));
		direccion.setDirVia(dirViaTextField.getText());
		direccion.setLetra(SwingUtils.getTextFieldValueOrNull(letraTextField));
		direccion.setTipoVia(tipoViaTextField.getText().trim());
		direccion.setPiso(SwingUtils.getIntegerValueOrNull(pisoTextField));
		direccion.setNombreLocalidad(localidadTextField.getText());
		direccion.setNombreVia(nombreViaTextField.getText());
		
		empleado.setDireccion(direccion);
		return empleado;
	}
	
	public char[] getRepeatPassword() {
		return this.repeatPasswordField.getPassword();
	}

}

package com.pinguela.retroworld.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.ui.desktop.controller.OpenChangePasswordAction;
import com.pinguela.retroworld.ui.desktop.controller.UpdateEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.renderer.LocalidadListCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;

public class EmpleadoProfileView extends JPanel {
	private Logger logger = LogManager.getLogger(EmpleadoProfileView.class);

	public static final String PROFILE_PROPERTY = "updateProfile";

	private Empleado empleado;
	private Direccion direccion;

	private JTextField telefonoTextField;
	private JTextField emailTextField;
	private JTextField documentoTextField;
	private JTextField apellido1TextField;
	private JTextField apellido2TextField;
	private JTextField tipoViaTextField;
	private JTextField nombreViaTextField;
	private JTextField dirViaTextField;
	private JTextField pisoTextField;
	private JTextField letraTextField;
	private JTextField nameTextField;
	private JLabel idValueLbl;
	private JLabel userNameValueLbl;
	private JLabel localidadValueLbl;
	private JButton saveEditsButton;
	private JButton cancelEditsButton;
	private JComboBox localidadComboBox;
	private List<JTextField> multipleTextFields;


	public EmpleadoProfileView(Empleado e) {
		empleado = e;
		setLayout(new BorderLayout(0, 0));

		JPanel profileDetailPane = new JPanel();
		add(profileDetailPane, BorderLayout.CENTER);
		GridBagLayout gbl_profileDetailPane = new GridBagLayout();
		gbl_profileDetailPane.columnWidths = new int[]{0, 0, 0, 0, 101, 74, 50, 96, 86, 67, 73, 77, 62, 0, 0};
		gbl_profileDetailPane.rowHeights = new int[]{0, 41, 41, 62, 0, 25, 31, 28, 0, 27, 0, 0, 0, 25, 0, 20, 63, 0, 0, 0, 0};
		gbl_profileDetailPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_profileDetailPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		profileDetailPane.setLayout(gbl_profileDetailPane);

		JLabel userIconLbl = new JLabel("");
		userIconLbl.setIcon(new ImageIcon(EmpleadoProfileView.class.getResource("/icons/icons8-user-64.png")));
		GridBagConstraints gbc_userIconLbl = new GridBagConstraints();
		gbc_userIconLbl.anchor = GridBagConstraints.NORTHEAST;
		gbc_userIconLbl.gridheight = 2;
		gbc_userIconLbl.insets = new Insets(0, 0, 5, 5);
		gbc_userIconLbl.gridx = 4;
		gbc_userIconLbl.gridy = 2;
		profileDetailPane.add(userIconLbl, gbc_userIconLbl);

		userNameValueLbl = new JLabel("New label");
		GridBagConstraints gbc_userNameValueLbl = new GridBagConstraints();
		gbc_userNameValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_userNameValueLbl.gridx = 5;
		gbc_userNameValueLbl.gridy = 2;
		profileDetailPane.add(userNameValueLbl, gbc_userNameValueLbl);

		idValueLbl = new JLabel("New label");
		GridBagConstraints gbc_idValueLbl = new GridBagConstraints();
		gbc_idValueLbl.anchor = GridBagConstraints.NORTH;
		gbc_idValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLbl.gridx = 5;
		gbc_idValueLbl.gridy = 3;
		profileDetailPane.add(idValueLbl, gbc_idValueLbl);

		JButton editButtonLbl = new JButton("");
		editButtonLbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditInterface(true);
			}
		});
		editButtonLbl.setIcon(new ImageIcon(EmpleadoProfileView.class.getResource("/icons/icons8-nut-64.png")));
		GridBagConstraints gbc_editButtonLbl = new GridBagConstraints();
		gbc_editButtonLbl.gridheight = 2;
		gbc_editButtonLbl.anchor = GridBagConstraints.NORTH;
		gbc_editButtonLbl.insets = new Insets(0, 0, 5, 5);
		gbc_editButtonLbl.gridx = 12;
		gbc_editButtonLbl.gridy = 2;
		profileDetailPane.add(editButtonLbl, gbc_editButtonLbl);

		JLabel nameLbl = new JLabel("Nombre:");
		nameLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 4;
		gbc_nameLbl.gridy = 5;
		profileDetailPane.add(nameLbl, gbc_nameLbl);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.gridx = 5;
		gbc_nameTextField.gridy = 5;
		profileDetailPane.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel apellido1Lbl = new JLabel("Primer apellido:");
		apellido1Lbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_apellido1Lbl = new GridBagConstraints();
		gbc_apellido1Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Lbl.gridx = 8;
		gbc_apellido1Lbl.gridy = 5;
		profileDetailPane.add(apellido1Lbl, gbc_apellido1Lbl);

		apellido1TextField = new JTextField();
		GridBagConstraints gbc_apellido1TextField = new GridBagConstraints();
		gbc_apellido1TextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TextField.fill = GridBagConstraints.BOTH;
		gbc_apellido1TextField.gridx = 9;
		gbc_apellido1TextField.gridy = 5;
		profileDetailPane.add(apellido1TextField, gbc_apellido1TextField);
		apellido1TextField.setColumns(10);

		JLabel telefonoLbl = new JLabel("Teléfono:");
		telefonoLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_telefonoLbl = new GridBagConstraints();
		gbc_telefonoLbl.anchor = GridBagConstraints.EAST;
		gbc_telefonoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLbl.gridx = 4;
		gbc_telefonoLbl.gridy = 7;
		profileDetailPane.add(telefonoLbl, gbc_telefonoLbl);

		telefonoTextField = new JTextField();
		GridBagConstraints gbc_telefonoTextField = new GridBagConstraints();
		gbc_telefonoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTextField.fill = GridBagConstraints.BOTH;
		gbc_telefonoTextField.gridx = 5;
		gbc_telefonoTextField.gridy = 7;
		profileDetailPane.add(telefonoTextField, gbc_telefonoTextField);
		telefonoTextField.setColumns(10);

		JLabel apellido2Lbl = new JLabel("Segundo apellido:");
		apellido2Lbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_apellido2Lbl = new GridBagConstraints();
		gbc_apellido2Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Lbl.gridx = 8;
		gbc_apellido2Lbl.gridy = 7;
		profileDetailPane.add(apellido2Lbl, gbc_apellido2Lbl);

		apellido2TextField = new JTextField();
		GridBagConstraints gbc_apellido2TextField = new GridBagConstraints();
		gbc_apellido2TextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2TextField.fill = GridBagConstraints.BOTH;
		gbc_apellido2TextField.gridx = 9;
		gbc_apellido2TextField.gridy = 7;
		profileDetailPane.add(apellido2TextField, gbc_apellido2TextField);
		apellido2TextField.setColumns(10);

		JLabel documentoLbl = new JLabel("DNI/NIE:");
		documentoLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_documentoLbl = new GridBagConstraints();
		gbc_documentoLbl.anchor = GridBagConstraints.EAST;
		gbc_documentoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_documentoLbl.gridx = 4;
		gbc_documentoLbl.gridy = 9;
		profileDetailPane.add(documentoLbl, gbc_documentoLbl);

		documentoTextField = new JTextField();
		GridBagConstraints gbc_documentoTextField = new GridBagConstraints();
		gbc_documentoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_documentoTextField.fill = GridBagConstraints.BOTH;
		gbc_documentoTextField.gridx = 5;
		gbc_documentoTextField.gridy = 9;
		profileDetailPane.add(documentoTextField, gbc_documentoTextField);
		documentoTextField.setColumns(10);

		JLabel emailLbl = new JLabel("Email:");
		emailLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 8;
		gbc_emailLbl.gridy = 9;
		profileDetailPane.add(emailLbl, gbc_emailLbl);

		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.gridwidth = 2;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.BOTH;
		gbc_emailTextField.gridx = 9;
		gbc_emailTextField.gridy = 9;
		profileDetailPane.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel direccionTitleLbl = new JLabel("Dirección");
		direccionTitleLbl.setFont(new Font("Noto Sans", Font.BOLD, 18));
		GridBagConstraints gbc_direccionTitleLbl = new GridBagConstraints();
		gbc_direccionTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_direccionTitleLbl.gridx = 5;
		gbc_direccionTitleLbl.gridy = 11;
		profileDetailPane.add(direccionTitleLbl, gbc_direccionTitleLbl);

		JLabel tipoViaLbl = new JLabel("Tipo vía:");
		tipoViaLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_tipoViaLbl = new GridBagConstraints();
		gbc_tipoViaLbl.anchor = GridBagConstraints.EAST;
		gbc_tipoViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaLbl.gridx = 4;
		gbc_tipoViaLbl.gridy = 13;
		profileDetailPane.add(tipoViaLbl, gbc_tipoViaLbl);

		tipoViaTextField = new JTextField();
		GridBagConstraints gbc_tipoViaTextField = new GridBagConstraints();
		gbc_tipoViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaTextField.fill = GridBagConstraints.BOTH;
		gbc_tipoViaTextField.gridx = 5;
		gbc_tipoViaTextField.gridy = 13;
		profileDetailPane.add(tipoViaTextField, gbc_tipoViaTextField);
		tipoViaTextField.setColumns(10);

		JLabel nombreViaLbl = new JLabel("Nombre vía:");
		nombreViaLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_nombreViaLbl = new GridBagConstraints();
		gbc_nombreViaLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaLbl.gridx = 8;
		gbc_nombreViaLbl.gridy = 13;
		profileDetailPane.add(nombreViaLbl, gbc_nombreViaLbl);

		nombreViaTextField = new JTextField();
		GridBagConstraints gbc_nombreViaTextField = new GridBagConstraints();
		gbc_nombreViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaTextField.fill = GridBagConstraints.BOTH;
		gbc_nombreViaTextField.gridx = 9;
		gbc_nombreViaTextField.gridy = 13;
		profileDetailPane.add(nombreViaTextField, gbc_nombreViaTextField);
		nombreViaTextField.setColumns(10);

		JLabel dirViaLbl = new JLabel("Número:");
		dirViaLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_dirViaLbl = new GridBagConstraints();
		gbc_dirViaLbl.anchor = GridBagConstraints.EAST;
		gbc_dirViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaLbl.gridx = 11;
		gbc_dirViaLbl.gridy = 13;
		profileDetailPane.add(dirViaLbl, gbc_dirViaLbl);

		dirViaTextField = new JTextField();
		GridBagConstraints gbc_dirViaTextField = new GridBagConstraints();
		gbc_dirViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaTextField.fill = GridBagConstraints.BOTH;
		gbc_dirViaTextField.gridx = 12;
		gbc_dirViaTextField.gridy = 13;
		profileDetailPane.add(dirViaTextField, gbc_dirViaTextField);
		dirViaTextField.setColumns(10);

		JLabel pisoLbl = new JLabel("Piso:");
		pisoLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_pisoLbl = new GridBagConstraints();
		gbc_pisoLbl.anchor = GridBagConstraints.EAST;
		gbc_pisoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pisoLbl.gridx = 4;
		gbc_pisoLbl.gridy = 15;
		profileDetailPane.add(pisoLbl, gbc_pisoLbl);

		pisoTextField = new JTextField();
		GridBagConstraints gbc_pisoTextField = new GridBagConstraints();
		gbc_pisoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pisoTextField.fill = GridBagConstraints.BOTH;
		gbc_pisoTextField.gridx = 5;
		gbc_pisoTextField.gridy = 15;
		profileDetailPane.add(pisoTextField, gbc_pisoTextField);
		pisoTextField.setColumns(10);

		JLabel letraLbl = new JLabel("Letra:");
		letraLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_letraLbl = new GridBagConstraints();
		gbc_letraLbl.anchor = GridBagConstraints.EAST;
		gbc_letraLbl.insets = new Insets(0, 0, 5, 5);
		gbc_letraLbl.gridx = 8;
		gbc_letraLbl.gridy = 15;
		profileDetailPane.add(letraLbl, gbc_letraLbl);

		letraTextField = new JTextField();
		GridBagConstraints gbc_letraTextField = new GridBagConstraints();
		gbc_letraTextField.insets = new Insets(0, 0, 5, 5);
		gbc_letraTextField.fill = GridBagConstraints.BOTH;
		gbc_letraTextField.gridx = 9;
		gbc_letraTextField.gridy = 15;
		profileDetailPane.add(letraTextField, gbc_letraTextField);
		letraTextField.setColumns(10);

		JLabel localidadLbl = new JLabel("Localidad:");
		localidadLbl.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_localidadLbl = new GridBagConstraints();
		gbc_localidadLbl.anchor = GridBagConstraints.EAST;
		gbc_localidadLbl.insets = new Insets(0, 0, 5, 5);
		gbc_localidadLbl.gridx = 11;
		gbc_localidadLbl.gridy = 15;
		profileDetailPane.add(localidadLbl, gbc_localidadLbl);

		localidadValueLbl = new JLabel("New label");
		GridBagConstraints gbc_localidadValueLbl = new GridBagConstraints();
		gbc_localidadValueLbl.anchor = GridBagConstraints.WEST;
		gbc_localidadValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_localidadValueLbl.gridx = 12;
		gbc_localidadValueLbl.gridy = 15;
		profileDetailPane.add(localidadValueLbl, gbc_localidadValueLbl);

		localidadComboBox = new JComboBox();
		GridBagConstraints gbc_localidadComboBox = new GridBagConstraints();
		gbc_localidadComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_localidadComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_localidadComboBox.gridx = 12;
		gbc_localidadComboBox.gridy = 15;
		profileDetailPane.add(localidadComboBox, gbc_localidadComboBox);

		JButton changePasswordButton = new JButton("Cambiar clave de acceso");
		changePasswordButton.addActionListener(new OpenChangePasswordAction());
		GridBagConstraints gbc_changePasswordButton = new GridBagConstraints();
		gbc_changePasswordButton.gridwidth = 2;
		gbc_changePasswordButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_changePasswordButton.insets = new Insets(0, 0, 5, 5);
		gbc_changePasswordButton.gridx = 5;
		gbc_changePasswordButton.gridy = 17;
		profileDetailPane.add(changePasswordButton, gbc_changePasswordButton);

		saveEditsButton = new JButton();
		saveEditsButton.setAction(new UpdateEmpleadoAction(this, "Guardar"));
		GridBagConstraints gbc_saveEditsButton = new GridBagConstraints();
		gbc_saveEditsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveEditsButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveEditsButton.gridx = 7;
		gbc_saveEditsButton.gridy = 19;
		profileDetailPane.add(saveEditsButton, gbc_saveEditsButton);

		cancelEditsButton = new JButton("Cancelar");
		cancelEditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateView();
				showEditInterface(false);	
			}
		});
		GridBagConstraints gbc_cancelEditsButton = new GridBagConstraints();
		gbc_cancelEditsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelEditsButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelEditsButton.gridx = 8;
		gbc_cancelEditsButton.gridy = 19;
		profileDetailPane.add(cancelEditsButton, gbc_cancelEditsButton);

		postInitialize();
	}

	public void postInitialize() {
		try {
			direccion = empleado.getDireccion();
			multipleTextFields = new ArrayList<JTextField>();
			updateView();

			multipleTextFields.add(nameTextField);
			multipleTextFields.add(telefonoTextField);
			multipleTextFields.add(emailTextField);
			multipleTextFields.add(apellido1TextField);
			multipleTextFields.add(apellido2TextField);
			multipleTextFields.add(documentoTextField);
			multipleTextFields.add(tipoViaTextField);
			multipleTextFields.add(nombreViaTextField);
			multipleTextFields.add(dirViaTextField);
			multipleTextFields.add(pisoTextField);
			multipleTextFields.add(letraTextField);

			showEditInterface(false);
			localidadComboBox.setRenderer(new LocalidadListCellRenderer());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateView() {
		idValueLbl.setText("#"+String.valueOf(empleado.getId()));
		userNameValueLbl.setText(empleado.getNombre());
		nameTextField.setText(empleado.getNombre());
		telefonoTextField.setText(empleado.getTelefono());
		emailTextField.setText(empleado.getEmail());
		apellido1TextField.setText(empleado.getApellido1());
		apellido2TextField.setText(empleado.getApellido2());
		documentoTextField.setText(empleado.getDocumentoIdentidad());
		tipoViaTextField.setText(direccion.getTipoVia());
		nombreViaTextField.setText(direccion.getNombreVia());
		dirViaTextField.setText(direccion.getDirVia());
		pisoTextField.setText(String.valueOf(direccion.getPiso()));
		letraTextField.setText(direccion.getLetra());
		localidadValueLbl.setText(direccion.getNombreLocalidad());		
	}
	
	public void setModel(DefaultComboBoxModel<Localidad> localidadModel) {
		localidadComboBox.setModel(localidadModel);
		
	}
	
	public void updateEmpleado() {
		Localidad localidad = (Localidad)localidadComboBox.getSelectedItem();
		direccion.setCodigoPostal(localidad.getCodigoPostal());
		direccion.setNombreLocalidad(localidad.getNombre());
		direccion.setDirVia(dirViaTextField.getText().trim());
		direccion.setNombreVia(nombreViaTextField.getText().trim());
		direccion.setTipoVia(tipoViaTextField.getText().trim());
		direccion.setIdEmpleado(empleado.getId());
		direccion.setLetra(letraTextField.getText().trim());
		direccion.setPiso(Integer.valueOf(pisoTextField.getText().trim()));
		
		empleado.setNombre(nameTextField.getText().trim());
		empleado.setApellido1(apellido1TextField.getText().trim());
		empleado.setApellido2(apellido2TextField.getText().trim());
		empleado.setDocumentoIdentidad(documentoTextField.getText().trim());
		empleado.setEmail(emailTextField.getText().trim());
		empleado.setTelefono(telefonoTextField.getText().trim());
		empleado.setDireccion(direccion);
		showEditInterface(false);
	}

	private void showEditInterface(boolean editable) {
		SwingUtils.setEditableMultipleTextField(multipleTextFields, editable);
		saveEditsButton.setVisible(editable);
		cancelEditsButton.setVisible(editable);
		localidadComboBox.setVisible(editable);
		if(editable==true) {
			localidadValueLbl.setVisible(false);
		} else {
			localidadValueLbl.setVisible(true);
		}
	}
	
	public Direccion getDireccion() {
		return this.direccion;
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}
	
}

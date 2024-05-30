package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.ui.desktop.model.LineaPedidoTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.LineaPedidoTableCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import javax.swing.JSeparator;

public class PedidoDetailDialog extends RWDialog {

	
	private Usuario usuario;
	private Pedido pedido;
	List<Direccion> direcciones;
	List<JTextField> multipleTextField;
	private JTextField nombreTextField;
	private JTextField apellido1TextField;
	private JTextField apellido2TextField;
	private JTextField telefonoTextField;
	private JTextField emailTextField;
	private JTextField documentoTextField;
	private JTextField tipoViaTextField;
	private JTextField nombreViaTextField;
	private JTextField numeroTextField;
	private JTextField pisoTextField;
	private JTextField letraTextField;
	private JTextField localidadTextField;
	private JLabel idValueLbl;
	private JTable lineasPedidoTable;


	public PedidoDetailDialog() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pedidoDataPanel = new JPanel();
		getContentPane().add(pedidoDataPanel, BorderLayout.NORTH);
		GridBagLayout gbl_pedidoDataPanel = new GridBagLayout();
		gbl_pedidoDataPanel.columnWidths = new int[]{45, 0, 0, 0, 310, 0, 0, 0, 0};
		gbl_pedidoDataPanel.rowHeights = new int[]{28, 43, 0, 27, 36, 26, 33, 28, 0, 47, 35, 0, 29, 53};
		gbl_pedidoDataPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pedidoDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pedidoDataPanel.setLayout(gbl_pedidoDataPanel);
		
		JLabel pedidoTitleLbl = new JLabel("Pedido:");
		pedidoTitleLbl.setFont(new Font("Arial", Font.BOLD, 18));
		GridBagConstraints gbc_pedidoTitleLbl = new GridBagConstraints();
		gbc_pedidoTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pedidoTitleLbl.gridx = 0;
		gbc_pedidoTitleLbl.gridy = 0;
		pedidoDataPanel.add(pedidoTitleLbl, gbc_pedidoTitleLbl);
		
		idValueLbl = new JLabel("New label");
		idValueLbl.setForeground(new Color(128, 128, 128));
		idValueLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		GridBagConstraints gbc_idValueLbl = new GridBagConstraints();
		gbc_idValueLbl.anchor = GridBagConstraints.WEST;
		gbc_idValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLbl.gridx = 1;
		gbc_idValueLbl.gridy = 0;
		pedidoDataPanel.add(idValueLbl, gbc_idValueLbl);
		
		JLabel lineasLbl = new JLabel("Lineas");
		lineasLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lineasLbl = new GridBagConstraints();
		gbc_lineasLbl.gridwidth = 4;
		gbc_lineasLbl.insets = new Insets(0, 0, 0, 5);
		gbc_lineasLbl.gridx = 0;
		gbc_lineasLbl.gridy = 14;
		pedidoDataPanel.add(lineasLbl, gbc_lineasLbl);
		
		JLabel datosClienteLbl = new JLabel("Datos del cliente");
		datosClienteLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_datosClienteLbl = new GridBagConstraints();
		gbc_datosClienteLbl.gridwidth = 4;
		gbc_datosClienteLbl.insets = new Insets(0, 0, 5, 5);
		gbc_datosClienteLbl.gridx = 0;
		gbc_datosClienteLbl.gridy = 2;
		pedidoDataPanel.add(datosClienteLbl, gbc_datosClienteLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_nombreLbl = new GridBagConstraints();
		gbc_nombreLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLbl.gridx = 0;
		gbc_nombreLbl.gridy = 4;
		pedidoDataPanel.add(nombreLbl, gbc_nombreLbl);
		
		nombreTextField = new JTextField();
		GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
		gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTextField.gridx = 1;
		gbc_nombreTextField.gridy = 4;
		pedidoDataPanel.add(nombreTextField, gbc_nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel apellido1Lbl = new JLabel("Primer apellido:");
		apellido1Lbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_apellido1Lbl = new GridBagConstraints();
		gbc_apellido1Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Lbl.gridx = 3;
		gbc_apellido1Lbl.gridy = 4;
		pedidoDataPanel.add(apellido1Lbl, gbc_apellido1Lbl);
		
		apellido1TextField = new JTextField();
		GridBagConstraints gbc_apellido1TextField = new GridBagConstraints();
		gbc_apellido1TextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TextField.gridx = 4;
		gbc_apellido1TextField.gridy = 4;
		pedidoDataPanel.add(apellido1TextField, gbc_apellido1TextField);
		apellido1TextField.setColumns(10);
		
		JLabel apellido2Lbl = new JLabel("Segundo apellido:");
		apellido2Lbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_apellido2Lbl = new GridBagConstraints();
		gbc_apellido2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Lbl.anchor = GridBagConstraints.EAST;
		gbc_apellido2Lbl.gridx = 6;
		gbc_apellido2Lbl.gridy = 4;
		pedidoDataPanel.add(apellido2Lbl, gbc_apellido2Lbl);
		
		apellido2TextField = new JTextField();
		GridBagConstraints gbc_apellido2TextField = new GridBagConstraints();
		gbc_apellido2TextField.insets = new Insets(0, 0, 5, 0);
		gbc_apellido2TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TextField.gridx = 7;
		gbc_apellido2TextField.gridy = 4;
		pedidoDataPanel.add(apellido2TextField, gbc_apellido2TextField);
		apellido2TextField.setColumns(10);
		
		JLabel telefonoLbl = new JLabel("Teléfono:");
		telefonoLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_telefonoLbl = new GridBagConstraints();
		gbc_telefonoLbl.anchor = GridBagConstraints.EAST;
		gbc_telefonoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLbl.gridx = 0;
		gbc_telefonoLbl.gridy = 6;
		pedidoDataPanel.add(telefonoLbl, gbc_telefonoLbl);
		
		telefonoTextField = new JTextField();
		GridBagConstraints gbc_telefonoTextField = new GridBagConstraints();
		gbc_telefonoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTextField.gridx = 1;
		gbc_telefonoTextField.gridy = 6;
		pedidoDataPanel.add(telefonoTextField, gbc_telefonoTextField);
		telefonoTextField.setColumns(10);
		
		JLabel emailLbl = new JLabel("Email:");
		emailLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 3;
		gbc_emailLbl.gridy = 6;
		pedidoDataPanel.add(emailLbl, gbc_emailLbl);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 4;
		gbc_emailTextField.gridy = 6;
		pedidoDataPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel documentoLbl = new JLabel("DNI/NIE");
		documentoLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_documentoLbl = new GridBagConstraints();
		gbc_documentoLbl.anchor = GridBagConstraints.EAST;
		gbc_documentoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_documentoLbl.gridx = 6;
		gbc_documentoLbl.gridy = 6;
		pedidoDataPanel.add(documentoLbl, gbc_documentoLbl);
		
		documentoTextField = new JTextField();
		GridBagConstraints gbc_documentoTextField = new GridBagConstraints();
		gbc_documentoTextField.insets = new Insets(0, 0, 5, 0);
		gbc_documentoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentoTextField.gridx = 7;
		gbc_documentoTextField.gridy = 6;
		pedidoDataPanel.add(documentoTextField, gbc_documentoTextField);
		documentoTextField.setColumns(10);
		
		JLabel datosDireccionLbl = new JLabel("Datos de envío");
		datosDireccionLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_datosDireccionLbl = new GridBagConstraints();
		gbc_datosDireccionLbl.gridwidth = 4;
		gbc_datosDireccionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_datosDireccionLbl.gridx = 0;
		gbc_datosDireccionLbl.gridy = 8;
		pedidoDataPanel.add(datosDireccionLbl, gbc_datosDireccionLbl);
		
		JLabel tipoViaLbl = new JLabel("Tipo vía:");
		tipoViaLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_tipoViaLbl = new GridBagConstraints();
		gbc_tipoViaLbl.anchor = GridBagConstraints.EAST;
		gbc_tipoViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaLbl.gridx = 0;
		gbc_tipoViaLbl.gridy = 10;
		pedidoDataPanel.add(tipoViaLbl, gbc_tipoViaLbl);
		
		tipoViaTextField = new JTextField();
		GridBagConstraints gbc_tipoViaTextField = new GridBagConstraints();
		gbc_tipoViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tipoViaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoViaTextField.gridx = 1;
		gbc_tipoViaTextField.gridy = 10;
		pedidoDataPanel.add(tipoViaTextField, gbc_tipoViaTextField);
		tipoViaTextField.setColumns(10);
		
		JLabel nombreViaLbl = new JLabel("Nombre vía:");
		nombreViaLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_nombreViaLbl = new GridBagConstraints();
		gbc_nombreViaLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreViaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaLbl.gridx = 3;
		gbc_nombreViaLbl.gridy = 10;
		pedidoDataPanel.add(nombreViaLbl, gbc_nombreViaLbl);
		
		nombreViaTextField = new JTextField();
		GridBagConstraints gbc_nombreViaTextField = new GridBagConstraints();
		gbc_nombreViaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreViaTextField.gridx = 4;
		gbc_nombreViaTextField.gridy = 10;
		pedidoDataPanel.add(nombreViaTextField, gbc_nombreViaTextField);
		nombreViaTextField.setColumns(10);
		
		JLabel numeroLbl = new JLabel("Número:");
		numeroLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_numeroLbl = new GridBagConstraints();
		gbc_numeroLbl.anchor = GridBagConstraints.EAST;
		gbc_numeroLbl.insets = new Insets(0, 0, 5, 5);
		gbc_numeroLbl.gridx = 6;
		gbc_numeroLbl.gridy = 10;
		pedidoDataPanel.add(numeroLbl, gbc_numeroLbl);
		
		numeroTextField = new JTextField();
		GridBagConstraints gbc_numeroTextField = new GridBagConstraints();
		gbc_numeroTextField.insets = new Insets(0, 0, 5, 0);
		gbc_numeroTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_numeroTextField.gridx = 7;
		gbc_numeroTextField.gridy = 10;
		pedidoDataPanel.add(numeroTextField, gbc_numeroTextField);
		numeroTextField.setColumns(10);
		
		JLabel pisoLbl = new JLabel("Piso:");
		pisoLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_pisoLbl = new GridBagConstraints();
		gbc_pisoLbl.anchor = GridBagConstraints.EAST;
		gbc_pisoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pisoLbl.gridx = 0;
		gbc_pisoLbl.gridy = 12;
		pedidoDataPanel.add(pisoLbl, gbc_pisoLbl);
		
		pisoTextField = new JTextField();
		GridBagConstraints gbc_pisoTextField = new GridBagConstraints();
		gbc_pisoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pisoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pisoTextField.gridx = 1;
		gbc_pisoTextField.gridy = 12;
		pedidoDataPanel.add(pisoTextField, gbc_pisoTextField);
		pisoTextField.setColumns(10);
		
		JLabel letraLbl = new JLabel("Letra:");
		letraLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_letraLbl = new GridBagConstraints();
		gbc_letraLbl.anchor = GridBagConstraints.EAST;
		gbc_letraLbl.insets = new Insets(0, 0, 5, 5);
		gbc_letraLbl.gridx = 3;
		gbc_letraLbl.gridy = 12;
		pedidoDataPanel.add(letraLbl, gbc_letraLbl);
		
		letraTextField = new JTextField();
		GridBagConstraints gbc_letraTextField = new GridBagConstraints();
		gbc_letraTextField.insets = new Insets(0, 0, 5, 5);
		gbc_letraTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_letraTextField.gridx = 4;
		gbc_letraTextField.gridy = 12;
		pedidoDataPanel.add(letraTextField, gbc_letraTextField);
		letraTextField.setColumns(10);
		
		JLabel localidadLbl = new JLabel("Localidad:");
		localidadLbl.setFont(new Font("Arial", Font.BOLD, 13));
		GridBagConstraints gbc_localidadLbl = new GridBagConstraints();
		gbc_localidadLbl.anchor = GridBagConstraints.EAST;
		gbc_localidadLbl.insets = new Insets(0, 0, 5, 5);
		gbc_localidadLbl.gridx = 6;
		gbc_localidadLbl.gridy = 12;
		pedidoDataPanel.add(localidadLbl, gbc_localidadLbl);
		
		localidadTextField = new JTextField();
		GridBagConstraints gbc_localidadTextField = new GridBagConstraints();
		gbc_localidadTextField.insets = new Insets(0, 0, 5, 0);
		gbc_localidadTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_localidadTextField.gridx = 7;
		gbc_localidadTextField.gridy = 12;
		pedidoDataPanel.add(localidadTextField, gbc_localidadTextField);
		localidadTextField.setColumns(10);
		
		JScrollPane lineasPedidoScrollPane = new JScrollPane();
		getContentPane().add(lineasPedidoScrollPane, BorderLayout.CENTER);
		
		lineasPedidoTable = new JTable();
		lineasPedidoScrollPane.setViewportView(lineasPedidoTable);
		postInitialize();
		
	}
	
	private void postInitialize() {
		multipleTextField = new ArrayList<JTextField>();
		multipleTextField.add(nombreTextField);
		multipleTextField.add(apellido1TextField);
		multipleTextField.add(apellido2TextField);
		multipleTextField.add(telefonoTextField);
		multipleTextField.add(emailTextField);
		multipleTextField.add(documentoTextField);
		multipleTextField.add(tipoViaTextField);
		multipleTextField.add(nombreViaTextField);
		multipleTextField.add(numeroTextField);
		multipleTextField.add(pisoTextField);
		multipleTextField.add(letraTextField);
		multipleTextField.add(localidadTextField);
		SwingUtils.setEditableMultipleTextField(multipleTextField, false);
	}
	
	public void updateView() {
		idValueLbl.setText("#"+String.valueOf(pedido.getId()));
		nombreTextField.setText(usuario.getNombre());
		apellido1TextField.setText(usuario.getApellido1());
		if(usuario.getApellido2()!=null) {
			apellido2TextField.setText(usuario.getApellido2());			
		} else {
			apellido2TextField.setText("N/A");
		}
		telefonoTextField.setText(usuario.getTelefono());
		emailTextField.setText(usuario.getEmail());
		documentoTextField.setText(usuario.getDocumentoIdentidad());
		Direccion direccion = direcciones.get(0);
		tipoViaTextField.setText(direccion.getTipoVia());
		nombreViaTextField.setText(direccion.getNombreVia());
		numeroTextField.setText(direccion.getDirVia());
		pisoTextField.setText(String.valueOf(direccion.getPiso()));
		letraTextField.setText(direccion.getLetra());
		localidadTextField.setText(direccion.getNombreLocalidad());
	}
	
	public void setModel(LineaPedidoTableModel model) {
		lineasPedidoTable.setModel(model);
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	
	public void setDirecciones(List<Direccion>direcciones) {
		this.direcciones=direcciones;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido=pedido;
	}
	
	public void setTableCellRenderer(LineaPedidoTableCellRenderer renderer) {
		this.lineasPedidoTable.setDefaultRenderer(Object.class,renderer);
	}
}

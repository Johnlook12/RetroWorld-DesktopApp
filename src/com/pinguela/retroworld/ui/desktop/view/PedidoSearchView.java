package com.pinguela.retroworld.ui.desktop.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.CancelarPedidoAction;
import com.pinguela.retroworld.ui.desktop.controller.CompletarPedidoAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenPedidoAbiertoDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenPedidoCerradoDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.SearchPedidoAction;
import com.pinguela.retroworld.ui.desktop.model.PedidoAbiertoTableModel;
import com.pinguela.retroworld.ui.desktop.model.PedidoCerradoTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.PedidoTableCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.FormatUtils;

public class PedidoSearchView extends SearchView {
	public static final String ID_USUARIO = "1";
	public static final String ID_PEDIDO="2";
	public static final String ANUNCIO_ABIERTO="1";
	public static final String ANUNCIO_CERRADO="2";

	private List<Pedido>pedidosAbiertos;
	private List<Pedido>pedidosCerrados;
	private JTable cerradosTableResults;
	private ButtonGroup idButtonGroup;
	private ButtonGroup estadoAnuncioButtonGroup;
	private JTextField idValueTextField;
	private JRadioButton usuarioIdRadioButton;
	private JRadioButton pedidoIdRadioButton;
	private JRadioButton pedidoAbiertoRadioButton;
	private JRadioButton pedidoCerradoRadioButton;


	public PedidoSearchView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gridBagLayout);

		JLabel pedidoTitleLbl = new JLabel("Gestión de pedidos");
		pedidoTitleLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_pedidoTitleLbl = new GridBagConstraints();
		gbc_pedidoTitleLbl.gridwidth = 5;
		gbc_pedidoTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pedidoTitleLbl.gridx = 0;
		gbc_pedidoTitleLbl.gridy = 0;
		getSearchFieldPanel().add(pedidoTitleLbl, gbc_pedidoTitleLbl);

		pedidoIdRadioButton = new JRadioButton("ID Pedido");
		GridBagConstraints gbc_pedidoIdRadioButton = new GridBagConstraints();
		gbc_pedidoIdRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_pedidoIdRadioButton.gridx = 1;
		gbc_pedidoIdRadioButton.gridy = 1;
		getSearchFieldPanel().add(pedidoIdRadioButton, gbc_pedidoIdRadioButton);

		usuarioIdRadioButton = new JRadioButton("ID Usuario");
		GridBagConstraints gbc_usuarioIdRadioButton = new GridBagConstraints();
		gbc_usuarioIdRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_usuarioIdRadioButton.gridx = 3;
		gbc_usuarioIdRadioButton.gridy = 1;
		getSearchFieldPanel().add(usuarioIdRadioButton, gbc_usuarioIdRadioButton);

		cerradosTableResults = new JTable();

		idValueTextField = new JTextField();
		GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
		gbc_idValueTextField.gridwidth = 4;
		gbc_idValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idValueTextField.gridx = 4;
		gbc_idValueTextField.gridy = 1;
		getSearchFieldPanel().add(idValueTextField, gbc_idValueTextField);
		idValueTextField.setColumns(10);

		JButton buscarButton = new JButton("New button");
		buscarButton.setAction(new SearchPedidoAction(this,"Buscar"));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarButton.gridx = 9;
		gbc_buscarButton.gridy = 1;
		getSearchFieldPanel().add(buscarButton, gbc_buscarButton);

		pedidoAbiertoRadioButton = new JRadioButton("Pedidos abiertos");
		pedidoAbiertoRadioButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				getResultsScrollPane().setViewportView(getTableResults());
			}
		});
		GridBagConstraints gbc_pedidoAbiertoRadioButton = new GridBagConstraints();
		gbc_pedidoAbiertoRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_pedidoAbiertoRadioButton.gridx = 13;
		gbc_pedidoAbiertoRadioButton.gridy = 1;
		getSearchFieldPanel().add(pedidoAbiertoRadioButton, gbc_pedidoAbiertoRadioButton);

		pedidoCerradoRadioButton = new JRadioButton("Pedidos cerrados");
		pedidoCerradoRadioButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				getResultsScrollPane().setViewportView(cerradosTableResults);
			}
		});
		GridBagConstraints gbc_pedidoCerradoRadioButton = new GridBagConstraints();
		gbc_pedidoCerradoRadioButton.gridx = 15;
		gbc_pedidoCerradoRadioButton.gridy = 1;
		getSearchFieldPanel().add(pedidoCerradoRadioButton, gbc_pedidoCerradoRadioButton);


		postInitialize();
	}



	private void postInitialize() {
		idButtonGroup = new ButtonGroup();
		estadoAnuncioButtonGroup = new ButtonGroup();
		getTableResults().setDefaultRenderer(Object.class, new PedidoTableCellRenderer());
		cerradosTableResults.setDefaultRenderer(Object.class, new PedidoTableCellRenderer());
		FormatUtils.setOnlyDigitsDocument(idValueTextField);
		estadoAnuncioButtonGroup.add(pedidoAbiertoRadioButton);
		estadoAnuncioButtonGroup.add(pedidoCerradoRadioButton);
		idButtonGroup.add(usuarioIdRadioButton);
		idButtonGroup.add(pedidoIdRadioButton);

		usuarioIdRadioButton.setActionCommand(ID_USUARIO);
		pedidoIdRadioButton.setActionCommand(ID_PEDIDO);
		pedidoAbiertoRadioButton.setActionCommand(ANUNCIO_ABIERTO);
		pedidoCerradoRadioButton.setActionCommand(ANUNCIO_CERRADO);
		pedidoAbiertoRadioButton.setSelected(true);
		usuarioIdRadioButton.setSelected(true);
	}

	public void setPedidos(List<Pedido> pedidos) {
		List<Pedido> pedidosAbiertos = new ArrayList<Pedido>();
		List<Pedido> pedidosCerrados = new ArrayList<Pedido>();
		for(Pedido p:pedidos) {
			if(p.getIdEstado()!=EstadoPedido.CANCELADO && p.getIdEstado()!=EstadoPedido.COMPLETADO) {
				pedidosAbiertos.add(p);
			} else {
				pedidosCerrados.add(p);
			}
		}
		this.pedidosAbiertos=pedidosAbiertos;
		this.pedidosCerrados=pedidosCerrados;		
	}

	private Integer getSelectedRadioButton(ButtonGroup group) {
		Integer buttonValue = null;
		AbstractButton rb = null;
		for(Enumeration<AbstractButton> rbs = group.getElements(); rbs.hasMoreElements();) {
			rb = rbs.nextElement();
			if(rb.isSelected()) {
				buttonValue = Integer.valueOf(rb.getActionCommand());  
			}
		}
		return buttonValue;
	}

	public Integer getSelectedIdRadioButton() {
		return getSelectedRadioButton(idButtonGroup);
	}

	public Integer getSelectedEstadoRadioButton() {
		return getSelectedRadioButton(estadoAnuncioButtonGroup);
	}

	public void setModel(PedidoAbiertoTableModel abiertoModel, PedidoCerradoTableModel cerradoModel) {
		if(abiertoModel!=null) {
			getTableResults().setModel(abiertoModel);			
		}
		if(cerradoModel!=null) {
			cerradosTableResults.setModel(cerradoModel);			
		}
	}

	public List<Pedido> getPedidosAbiertos(){
		return this.pedidosAbiertos;
	}

	public List<Pedido> getPedidosCerrados(){
		return this.pedidosCerrados;
	}

	public Long getIdValue() {
		return idValueTextField.getText().isEmpty() ? null : Long.valueOf(idValueTextField.getText());
	}

	public JTable getCerradosTableResults() {
		return this.cerradosTableResults;
	}

	@Override
	public void addButtonsColumn() {
		ButtonColumn abiertoDetailButton = new ButtonColumn(getTableResults(), new OpenPedidoAbiertoDetailAction(this), 5, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1431_editors_editors_package_package.png")));
		ButtonColumn completarButton = new ButtonColumn(getTableResults(), new CompletarPedidoAction(this), 6, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1710_ok_yes_accept_green_ok_green_accept_yes.png")));
		ButtonColumn cancelarButton = new ButtonColumn(getTableResults(), new CancelarPedidoAction(this), 7, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1712_cancel_cancel.png")));			

		ButtonColumn cerradoDetailButton = new ButtonColumn(cerradosTableResults, new OpenPedidoCerradoDetailAction(this), 5, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1431_editors_editors_package_package.png")));			
	}

	@Override
	public AbstractCriteria getCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

}

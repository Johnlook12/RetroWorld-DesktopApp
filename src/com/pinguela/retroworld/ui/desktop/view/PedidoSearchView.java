package com.pinguela.retroworld.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.CancelarPedidoAction;
import com.pinguela.retroworld.ui.desktop.controller.CompletarPedidoAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenPedidoAbiertoDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenPedidoCerradoDetailAction;
import com.pinguela.retroworld.ui.desktop.model.PedidoAbiertoTableModel;
import com.pinguela.retroworld.ui.desktop.model.PedidoCerradoTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.PedidoTableCellRenderer;

public class PedidoSearchView extends SearchView {

	private List<Pedido>pedidosAbiertos;
	private List<Pedido>pedidosCerrados;
	private JTable cerradosTableResults;
	private ButtonGroup estadoButtonGroup;
	private JTextField idValueTextField;

	
	public PedidoSearchView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JRadioButton pedidoIdRadioButton = new JRadioButton("ID Pedido");
		GridBagConstraints gbc_pedidoIdRadioButton = new GridBagConstraints();
		gbc_pedidoIdRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_pedidoIdRadioButton.gridx = 1;
		gbc_pedidoIdRadioButton.gridy = 1;
		getSearchFieldPanel().add(pedidoIdRadioButton, gbc_pedidoIdRadioButton);
		
		JRadioButton usuarioIdRadioButton = new JRadioButton("ID Usuario");
		GridBagConstraints gbc_usuarioIdRadioButton = new GridBagConstraints();
		gbc_usuarioIdRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_usuarioIdRadioButton.gridx = 3;
		gbc_usuarioIdRadioButton.gridy = 1;
		getSearchFieldPanel().add(usuarioIdRadioButton, gbc_usuarioIdRadioButton);
		
		JScrollPane cerradosScrollPane = new JScrollPane();
		
		cerradosTableResults = new JTable();
		cerradosScrollPane.setViewportView(cerradosTableResults);
		
		JTabbedPane pedidosTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(pedidosTabbedPane, BorderLayout.CENTER);
		pedidosTabbedPane.addTab("Pedidos abiertos",getResultsScrollPane());
		pedidosTabbedPane.addTab("Pedidos cerrados", cerradosScrollPane);
		
		estadoButtonGroup = new ButtonGroup();
		estadoButtonGroup.add(usuarioIdRadioButton);
		estadoButtonGroup.add(pedidoIdRadioButton);
		
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
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarButton.gridx = 18;
		gbc_buscarButton.gridy = 1;
		getSearchFieldPanel().add(buscarButton, gbc_buscarButton);
		
		
		postInitialize();
	}
	
	
	
	private void postInitialize() {
		getTableResults().setDefaultRenderer(Object.class, new PedidoTableCellRenderer());
		cerradosTableResults.setDefaultRenderer(Object.class, new PedidoTableCellRenderer());
	}
	
	public void setPedidos(List<Pedido> pedidos) {
		List<Pedido> pedidosAbiertos = new ArrayList<Pedido>();
		for(Pedido p:pedidos) {
			if(p.getIdEstado()!=EstadoPedido.CANCELADO && p.getIdEstado()!=EstadoPedido.COMPLETADO) {
				pedidosAbiertos.add(p);
			}
		}
		this.pedidosAbiertos=pedidosAbiertos;
		
		List<Pedido> pedidosCerrados = new ArrayList<Pedido>();
		for(Pedido p:pedidos) {
			if(p.getIdEstado()==EstadoPedido.CANCELADO || p.getIdEstado()==EstadoPedido.COMPLETADO) {
				pedidosCerrados.add(p);
			}
		}
		this.pedidosCerrados=pedidosCerrados;
	}
	
	public void updateModel() {
		getTableResults().setModel(new PedidoAbiertoTableModel(pedidosAbiertos));
		cerradosTableResults.setModel(new PedidoCerradoTableModel(pedidosCerrados));
		addButtonsColumn();
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

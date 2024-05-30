package com.pinguela.retroworld.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.OpenCreateEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.controller.SearchEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.controller.SoftDeleteEmpleadoAction;
import com.pinguela.retroworld.ui.desktop.dialog.CreateEmpleadoDialog;
import com.pinguela.retroworld.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.EmpleadoTableCellRenderer;

public class EmpleadoSearchView extends SearchView {

	private static final long serialVersionUID = 1L;
	private JTextField emailTextField;
	private JTable empleadoTableResults;
	
	//model
	List<Empleado> results = null;
	
	public EmpleadoSearchView() {
		setLayout(new BorderLayout(0, 0));
		

		add(getSearchFieldPanel(), BorderLayout.NORTH);
		GridBagLayout gbl_searchFieldPane = new GridBagLayout();
		gbl_searchFieldPane.columnWidths = new int[]{39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFieldPane.rowHeights = new int[]{28, 0, 0, 0};
		gbl_searchFieldPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_searchFieldPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gbl_searchFieldPane);
		
		JLabel empleadoTitleLbl = new JLabel("Empleados");
		empleadoTitleLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_empleadoTitleLbl = new GridBagConstraints();
		gbc_empleadoTitleLbl.anchor = GridBagConstraints.SOUTH;
		gbc_empleadoTitleLbl.gridheight = 2;
		gbc_empleadoTitleLbl.gridwidth = 3;
		gbc_empleadoTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_empleadoTitleLbl.gridx = 0;
		gbc_empleadoTitleLbl.gridy = 0;
		getSearchFieldPanel().add(empleadoTitleLbl, gbc_empleadoTitleLbl);
		
		JButton addEmpleadoBtn = new JButton("Añadir");
		addEmpleadoBtn.addActionListener(new OpenCreateEmpleadoAction());
		addEmpleadoBtn.setIcon(new ImageIcon(EmpleadoSearchView.class.getResource("/nuvola/16x16/1727_add_add.png")));
		GridBagConstraints gbc_addEmpleadoBtn = new GridBagConstraints();
		gbc_addEmpleadoBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addEmpleadoBtn.gridx = 4;
		gbc_addEmpleadoBtn.gridy = 1;
		getSearchFieldPanel().add(addEmpleadoBtn, gbc_addEmpleadoBtn);
		
		JLabel emailLbl = new JLabel("Email:");
		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.gridx = 7;
		gbc_emailLbl.gridy = 1;
		getSearchFieldPanel().add(emailLbl, gbc_emailLbl);
		
		emailTextField = new JTextField();
		EmpleadoSearchView view = this;
		emailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(emailTextField.getText().length()>3) {
					new SearchEmpleadoAction(view).actionPerformed(null);
				} else {
					setTableModel(getTableModel());
					updateView();
				}
			}
		});
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.gridwidth = 2;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 8;
		gbc_emailTextField.gridy = 1;
		getSearchFieldPanel().add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel idLbl = new JLabel("ID:");
		GridBagConstraints gbc_idLbl = new GridBagConstraints();
		gbc_idLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idLbl.gridx = 12;
		gbc_idLbl.gridy = 1;
		getSearchFieldPanel().add(idLbl, gbc_idLbl);
		
		JSpinner idSpinner = new JSpinner();
		GridBagConstraints gbc_idSpinner = new GridBagConstraints();
		gbc_idSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_idSpinner.gridx = 13;
		gbc_idSpinner.gridy = 1;
		getSearchFieldPanel().add(idSpinner, gbc_idSpinner);
		
		JScrollPane resultsScrollPane = new JScrollPane();
		add(resultsScrollPane, BorderLayout.CENTER);
		resultsScrollPane.setViewportView(getTableResults());
		
		postInitialize();
	}
	
	private void postInitialize() {
		getTableResults().setDefaultRenderer(Object.class, new EmpleadoTableCellRenderer());
	}
	
	public String getEmail() {
		return this.emailTextField.getText();
	}
	
	public void setResults(List<Empleado> empleados) {
		this.results=empleados;
	}
	
	public void updateView() {
		setTableModel(getTableModel());
		addButtonsColumn();
	}

	@Override
	public void addButtonsColumn() {
		ButtonColumn deleteButton = new ButtonColumn(getTableResults(), new SoftDeleteEmpleadoAction(this), 5, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1815_no_no.png")));
	}

	@Override
	public AbstractCriteria getCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

}

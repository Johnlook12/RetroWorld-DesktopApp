package com.pinguela.retroworld.ui.desktop.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.AprobarAnuncioAction;
import com.pinguela.retroworld.ui.desktop.controller.ModerarAnuncioPagedSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenAnuncioDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.PagedSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.RechazarAnuncioAction;
import com.pinguela.retroworld.ui.desktop.model.ModerarAnunciosTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.ModerarAnuncioTableCellRenderer;
import com.toedter.calendar.JDateChooser;

public class ModerarAnunciosView extends PaginatedSearchView<Anuncio> {

	private static final long serialVersionUID = 1L;
		
	private JTextField tituloTextField;
	private JDateChooser fechaInicioDateChooser;
	private JSpinner idAnuncioSpinner;
	private JTextField videojuegoTextField;

	
	public ModerarAnunciosView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{19, 97, 32, 96, 62, 88, 62, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 20, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Gestión de anuncios pendientes");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getSearchFieldPanel().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel idAnuncioLbl = new JLabel("ID: ");
		GridBagConstraints gbc_idAnuncioLbl = new GridBagConstraints();
		gbc_idAnuncioLbl.anchor = GridBagConstraints.WEST;
		gbc_idAnuncioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idAnuncioLbl.gridx = 0;
		gbc_idAnuncioLbl.gridy = 2;
		getSearchFieldPanel().add(idAnuncioLbl, gbc_idAnuncioLbl);
		
		idAnuncioSpinner = new JSpinner();
		GridBagConstraints gbc_idAnuncioSpinner = new GridBagConstraints();
		gbc_idAnuncioSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_idAnuncioSpinner.anchor = GridBagConstraints.NORTH;
		gbc_idAnuncioSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idAnuncioSpinner.gridx = 1;
		gbc_idAnuncioSpinner.gridy = 2;
		getSearchFieldPanel().add(idAnuncioSpinner, gbc_idAnuncioSpinner);
		
		JLabel tituloLbl = new JLabel("Titulo:");
		GridBagConstraints gbc_tituloLbl = new GridBagConstraints();
		gbc_tituloLbl.anchor = GridBagConstraints.WEST;
		gbc_tituloLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLbl.gridx = 2;
		gbc_tituloLbl.gridy = 2;
		getSearchFieldPanel().add(tituloLbl, gbc_tituloLbl);
		
		tituloTextField = new JTextField();
		tituloTextField.addKeyListener(new ModerarAnuncioPagedSearchAction(PagedSearchAction.START, this));
		tituloTextField.setColumns(10);
		GridBagConstraints gbc_tituloTextField = new GridBagConstraints();
		gbc_tituloTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloTextField.anchor = GridBagConstraints.NORTH;
		gbc_tituloTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloTextField.gridx = 3;
		gbc_tituloTextField.gridy = 2;
		getSearchFieldPanel().add(tituloTextField, gbc_tituloTextField);
		
		JLabel fechaInicioLbl = new JLabel("Fecha inicio:");
		GridBagConstraints gbc_fechaInicioLbl = new GridBagConstraints();
		gbc_fechaInicioLbl.anchor = GridBagConstraints.WEST;
		gbc_fechaInicioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioLbl.gridx = 4;
		gbc_fechaInicioLbl.gridy = 2;
		getSearchFieldPanel().add(fechaInicioLbl, gbc_fechaInicioLbl);
		
		fechaInicioDateChooser = new JDateChooser();
		fechaInicioDateChooser.getCalendarButton().setForeground(Color.BLACK);
		fechaInicioDateChooser.getCalendarButton().setBackground(UIManager.getColor("Button.background"));
		fechaInicioDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaInicioDateChooser = new GridBagConstraints();
		gbc_fechaInicioDateChooser.anchor = GridBagConstraints.NORTHWEST;
		gbc_fechaInicioDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDateChooser.gridx = 5;
		gbc_fechaInicioDateChooser.gridy = 2;
		getSearchFieldPanel().add(fechaInicioDateChooser, gbc_fechaInicioDateChooser);
		
		JLabel videojuegoLbl = new JLabel("Videojuego: ");
		GridBagConstraints gbc_videojuegoLbl = new GridBagConstraints();
		gbc_videojuegoLbl.anchor = GridBagConstraints.WEST;
		gbc_videojuegoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoLbl.gridx = 6;
		gbc_videojuegoLbl.gridy = 2;
		getSearchFieldPanel().add(videojuegoLbl, gbc_videojuegoLbl);
		
		videojuegoTextField = new JTextField();
		videojuegoTextField.setColumns(10);
		GridBagConstraints gbc_videojuegoTextField = new GridBagConstraints();
		gbc_videojuegoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_videojuegoTextField.insets = new Insets(0, 0, 5, 0);
		gbc_videojuegoTextField.anchor = GridBagConstraints.NORTH;
		gbc_videojuegoTextField.gridwidth = 2;
		gbc_videojuegoTextField.gridx = 7;
		gbc_videojuegoTextField.gridy = 2;
		getSearchFieldPanel().add(videojuegoTextField, gbc_videojuegoTextField);
		
		postInitialize();
	}
	
	private void postInitialize() {
		new ModerarAnuncioPagedSearchAction(PagedSearchAction.START, this).doAction();
		getTableResults().setDefaultRenderer(Object.class, new ModerarAnuncioTableCellRenderer());
		SpinnerNumberModel model = new SpinnerNumberModel(0,0, Integer.MAX_VALUE, 1);
		idAnuncioSpinner.setModel(model);
	}
	
	

	public AnuncioCriteria getCriteria() {
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombre(tituloTextField.getText().trim());
		Long spinnerValue = Long.valueOf((Integer)idAnuncioSpinner.getValue());
		if(spinnerValue!=0) {
			criteria.setIdAnuncio(spinnerValue);			
		}
		criteria.setNombreVideojuego(videojuegoTextField.getText().trim());
		Date fechaInicio = fechaInicioDateChooser.getDate();
		criteria.setFechaInicio(fechaInicio);
		criteria.setIdEstadoAnuncio(Anuncio.ESTADO_PENDIENTE);
		return criteria;
	}

	public void setModel(ModerarAnunciosTableModel model) {
		this.getTableResults().setModel(model);
	}

	@Override
	public void addButtonsColumn() {
		ButtonColumn detailButton = new ButtonColumn(getTableResults(), new OpenAnuncioDetailAction(this), 4, 
				new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-eye-24.png")));
		ButtonColumn aprobarButton = new ButtonColumn(getTableResults(), new AprobarAnuncioAction(this), 5, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1711_active_yes_yes_active_ok_good_check_check_good_ok.png")));
		ButtonColumn rechazarButton = new ButtonColumn(getTableResults(), new RechazarAnuncioAction(this), 6, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1709_no_cancel_close_delete_exit_reject_remove_x_cancel_close_delete_exit_no_reject_remove_x.png")));
	}
}

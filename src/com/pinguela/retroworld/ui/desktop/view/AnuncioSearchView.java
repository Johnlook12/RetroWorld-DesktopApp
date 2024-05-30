package com.pinguela.retroworld.ui.desktop.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.swingx.JXLabel;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.OpenAnuncioDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.PaginacionInicialAction;
import com.pinguela.retroworld.ui.desktop.controller.SoftDeleteAnuncioAction;
import com.pinguela.retroworld.ui.desktop.model.AnuncioTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.AnuncioTableCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;

public class AnuncioSearchView extends PaginatedSearchView<Anuncio> {
	private static Logger logger = LogManager.getLogger(AnuncioSearchView.class);
	
	private JTextField tituloTextField;
	private JTextField videojuegoTextField;
	private JDateChooser fechaInicioDateChooser;
	private JDateChooser fechaFinDateChooser;
	private JSlider precioDesdeSlider;
	private JSlider precioHastaSlider;
	private ButtonGroup estadoButtonGroup = new ButtonGroup();
	private JComboBox<Idioma> idiomaComboBox;
	private JComboBox<Genero> generoComboBox;
	private JComboBox<Plataforma> plataformaComboBox;
	private JLabel precioDesdeSliderLbl;
	private JLabel precioHastaSliderLbl;
	private JSpinner idUsuarioSpinner;
	private JSpinner idEmpleadoSpinner;

	// Modelo de esta vista	
	private AnuncioCriteria criteria = null;

	
	
	
	public AnuncioSearchView() {
		GridBagLayout gbl_searchFieldPanel = new GridBagLayout();
		gbl_searchFieldPanel.columnWidths = new int[]{1, 30, 1, 59, 16, 36, 43, 32, 1, 9, 70, 70, 41, 28, 1, 35, 54, 30, 0};
		gbl_searchFieldPanel.rowHeights = new int[]{36, 0, 32, 0, 26, 36, 32, 30, 25, 0, 0, 0};
		gbl_searchFieldPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_searchFieldPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gbl_searchFieldPanel);
		
		JLabel anuncioLbl = new JLabel("Buscar Anuncio");
		anuncioLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_anuncioLbl = new GridBagConstraints();
		gbc_anuncioLbl.gridwidth = 5;
		gbc_anuncioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_anuncioLbl.gridx = 1;
		gbc_anuncioLbl.gridy = 1;
		getSearchFieldPanel().add(anuncioLbl, gbc_anuncioLbl);
		
		JLabel idAnuncioLbl = new JLabel("ID: ");
		GridBagConstraints gbc_idAnuncioLbl = new GridBagConstraints();
		gbc_idAnuncioLbl.anchor = GridBagConstraints.EAST;
		gbc_idAnuncioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idAnuncioLbl.gridx = 1;
		gbc_idAnuncioLbl.gridy = 3;
		getSearchFieldPanel().add(idAnuncioLbl, gbc_idAnuncioLbl);
		
		JSpinner idAnuncioSpinner = new JSpinner();
		GridBagConstraints gbc_idAnuncioSpinner = new GridBagConstraints();
		gbc_idAnuncioSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_idAnuncioSpinner.gridwidth = 2;
		gbc_idAnuncioSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idAnuncioSpinner.gridx = 2;
		gbc_idAnuncioSpinner.gridy = 3;
		getSearchFieldPanel().add(idAnuncioSpinner, gbc_idAnuncioSpinner);
		
		JLabel idiomaLbl = new JLabel("Idioma: ");
		GridBagConstraints gbc_idiomaLbl = new GridBagConstraints();
		gbc_idiomaLbl.anchor = GridBagConstraints.EAST;
		gbc_idiomaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaLbl.gridx = 5;
		gbc_idiomaLbl.gridy = 3;
		getSearchFieldPanel().add(idiomaLbl, gbc_idiomaLbl);
		
		idiomaComboBox = new JComboBox();
		idiomaComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_idiomaComboBox = new GridBagConstraints();
		gbc_idiomaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaComboBox.gridwidth = 2;
		gbc_idiomaComboBox.gridx = 6;
		gbc_idiomaComboBox.gridy = 3;
		getSearchFieldPanel().add(idiomaComboBox, gbc_idiomaComboBox);
		
		JLabel generoLbl = new JLabel("Género: ");
		GridBagConstraints gbc_generoLbl = new GridBagConstraints();
		gbc_generoLbl.anchor = GridBagConstraints.EAST;
		gbc_generoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_generoLbl.gridx = 9;
		gbc_generoLbl.gridy = 3;
		getSearchFieldPanel().add(generoLbl, gbc_generoLbl);
		
		generoComboBox = new JComboBox();
		generoComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		GridBagConstraints gbc_generoComboBox = new GridBagConstraints();
		gbc_generoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoComboBox.gridwidth = 2;
		gbc_generoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoComboBox.gridx = 10;
		gbc_generoComboBox.gridy = 3;
		getSearchFieldPanel().add(generoComboBox, gbc_generoComboBox);
		
		JXLabel plataformaLbl = new JXLabel();
		plataformaLbl.setText("Plataforma:");
		GridBagConstraints gbc_plataformaLbl = new GridBagConstraints();
		gbc_plataformaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_plataformaLbl.gridx = 13;
		gbc_plataformaLbl.gridy = 3;
		getSearchFieldPanel().add(plataformaLbl, gbc_plataformaLbl);
		
		plataformaComboBox = new JComboBox();
		GridBagConstraints gbc_plataformaComboBox = new GridBagConstraints();
		gbc_plataformaComboBox.gridwidth = 3;
		gbc_plataformaComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_plataformaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_plataformaComboBox.gridx = 15;
		gbc_plataformaComboBox.gridy = 3;
		getSearchFieldPanel().add(plataformaComboBox, gbc_plataformaComboBox);
		
		JLabel appIconLbl = new JLabel("");
		GridBagConstraints gbc_appIconLbl = new GridBagConstraints();
		gbc_appIconLbl.anchor = GridBagConstraints.WEST;
		gbc_appIconLbl.insets = new Insets(0, 0, 5, 5);
		gbc_appIconLbl.gridx = 0;
		gbc_appIconLbl.gridy = 4;
		getSearchFieldPanel().add(appIconLbl, gbc_appIconLbl);
		
		JLabel tituloLbl = new JLabel("Titulo:");
		GridBagConstraints gbc_tituloLbl = new GridBagConstraints();
		gbc_tituloLbl.anchor = GridBagConstraints.EAST;
		gbc_tituloLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLbl.gridx = 1;
		gbc_tituloLbl.gridy = 4;
		getSearchFieldPanel().add(tituloLbl, gbc_tituloLbl);
		
		tituloTextField = new JTextField();
		AnuncioSearchView view = this;
		tituloTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(tituloTextField.getText().length()>=3) {
					new PaginacionInicialAction(view).actionPerformed(null);
				}
			}
		});
		tituloTextField.setColumns(10);
		GridBagConstraints gbc_tituloTextField = new GridBagConstraints();
		gbc_tituloTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloTextField.gridwidth = 2;
		gbc_tituloTextField.gridx = 2;
		gbc_tituloTextField.gridy = 4;
		getSearchFieldPanel().add(tituloTextField, gbc_tituloTextField);
		
		JLabel fechaInicioLbl = new JLabel("Fecha inicio:");
		GridBagConstraints gbc_fechaInicioLbl = new GridBagConstraints();
		gbc_fechaInicioLbl.anchor = GridBagConstraints.EAST;
		gbc_fechaInicioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioLbl.gridx = 1;
		gbc_fechaInicioLbl.gridy = 5;
		getSearchFieldPanel().add(fechaInicioLbl, gbc_fechaInicioLbl);
		
		fechaInicioDateChooser = new JDateChooser();
		fechaInicioDateChooser.getCalendarButton().setBackground(new Color(240, 240, 240));
		fechaInicioDateChooser.getCalendarButton().setForeground(new Color(0, 0, 0));
		fechaInicioDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaInicioDateChooser = new GridBagConstraints();
		gbc_fechaInicioDateChooser.anchor = GridBagConstraints.WEST;
		gbc_fechaInicioDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDateChooser.gridwidth = 2;
		gbc_fechaInicioDateChooser.gridx = 2;
		gbc_fechaInicioDateChooser.gridy = 5;
		getSearchFieldPanel().add(fechaInicioDateChooser, gbc_fechaInicioDateChooser);
		
		JLabel precioLbl = new JLabel("Precio: ");
		GridBagConstraints gbc_precioLbl = new GridBagConstraints();
		gbc_precioLbl.anchor = GridBagConstraints.WEST;
		gbc_precioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioLbl.gridx = 5;
		gbc_precioLbl.gridy = 5;
		getSearchFieldPanel().add(precioLbl, gbc_precioLbl);
		
		JLabel precioDesdeLbl = new JLabel("Desde");
		GridBagConstraints gbc_precioDesdeLbl = new GridBagConstraints();
		gbc_precioDesdeLbl.anchor = GridBagConstraints.WEST;
		gbc_precioDesdeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeLbl.gridx = 6;
		gbc_precioDesdeLbl.gridy = 5;
		getSearchFieldPanel().add(precioDesdeLbl, gbc_precioDesdeLbl);
		
		precioDesdeSlider = new JSlider();
		precioDesdeSlider.setValue(0);
		precioDesdeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				precioDesdeSliderLbl.setText(String.valueOf(precioDesdeSlider.getValue()));
				new PaginacionInicialAction(view).actionPerformed(null);
			}
		});
		GridBagConstraints gbc_precioDesdeSlider = new GridBagConstraints();
		gbc_precioDesdeSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioDesdeSlider.anchor = GridBagConstraints.NORTH;
		gbc_precioDesdeSlider.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeSlider.gridwidth = 5;
		gbc_precioDesdeSlider.gridx = 7;
		gbc_precioDesdeSlider.gridy = 5;
		getSearchFieldPanel().add(precioDesdeSlider, gbc_precioDesdeSlider);
		
		JLabel precioHastaLbl = new JLabel("Hasta");
		GridBagConstraints gbc_precioHastaLbl = new GridBagConstraints();
		gbc_precioHastaLbl.anchor = GridBagConstraints.EAST;
		gbc_precioHastaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaLbl.gridx = 12;
		gbc_precioHastaLbl.gridy = 5;
		getSearchFieldPanel().add(precioHastaLbl, gbc_precioHastaLbl);
		
		precioHastaSlider = new JSlider();
		precioHastaSlider.setValue(100);
		precioHastaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				precioHastaSliderLbl.setText(String.valueOf(precioHastaSlider.getValue()));
				new PaginacionInicialAction(view).actionPerformed(null);
			}
		});
		GridBagConstraints gbc_precioHastaSlider = new GridBagConstraints();
		gbc_precioHastaSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioHastaSlider.anchor = GridBagConstraints.NORTH;
		gbc_precioHastaSlider.insets = new Insets(0, 0, 5, 0);
		gbc_precioHastaSlider.gridwidth = 5;
		gbc_precioHastaSlider.gridx = 13;
		gbc_precioHastaSlider.gridy = 5;
		getSearchFieldPanel().add(precioHastaSlider, gbc_precioHastaSlider);
		
		JLabel fechaFinLbl = new JLabel("Fecha fin: ");
		GridBagConstraints gbc_fechaFinLbl = new GridBagConstraints();
		gbc_fechaFinLbl.anchor = GridBagConstraints.EAST;
		gbc_fechaFinLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaFinLbl.gridx = 1;
		gbc_fechaFinLbl.gridy = 6;
		getSearchFieldPanel().add(fechaFinLbl, gbc_fechaFinLbl);
		
		fechaFinDateChooser = new JDateChooser();
		fechaFinDateChooser.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_fechaFinDateChooser = new GridBagConstraints();
		gbc_fechaFinDateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaFinDateChooser.gridwidth = 2;
		gbc_fechaFinDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaFinDateChooser.gridx = 2;
		gbc_fechaFinDateChooser.gridy = 6;
		getSearchFieldPanel().add(fechaFinDateChooser, gbc_fechaFinDateChooser);
		
		precioDesdeSliderLbl = new JLabel("");
		GridBagConstraints gbc_precioDesdeSliderLbl = new GridBagConstraints();
		gbc_precioDesdeSliderLbl.anchor = GridBagConstraints.WEST;
		gbc_precioDesdeSliderLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeSliderLbl.gridx = 10;
		gbc_precioDesdeSliderLbl.gridy = 6;
		getSearchFieldPanel().add(precioDesdeSliderLbl, gbc_precioDesdeSliderLbl);
		
		precioHastaSliderLbl = new JLabel("");
		GridBagConstraints gbc_precioHastaSliderLbl = new GridBagConstraints();
		gbc_precioHastaSliderLbl.anchor = GridBagConstraints.EAST;
		gbc_precioHastaSliderLbl.gridwidth = 2;
		gbc_precioHastaSliderLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaSliderLbl.gridx = 15;
		gbc_precioHastaSliderLbl.gridy = 6;
		getSearchFieldPanel().add(precioHastaSliderLbl, gbc_precioHastaSliderLbl);
		
		JLabel videojuegoLbl = new JLabel("Videojuego: ");
		GridBagConstraints gbc_videojuegoLbl = new GridBagConstraints();
		gbc_videojuegoLbl.anchor = GridBagConstraints.EAST;
		gbc_videojuegoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoLbl.gridx = 1;
		gbc_videojuegoLbl.gridy = 7;
		getSearchFieldPanel().add(videojuegoLbl, gbc_videojuegoLbl);
		
		videojuegoTextField = new JTextField();
		videojuegoTextField.setColumns(10);
		GridBagConstraints gbc_videojuegoTextField = new GridBagConstraints();
		gbc_videojuegoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_videojuegoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoTextField.gridwidth = 2;
		gbc_videojuegoTextField.gridx = 2;
		gbc_videojuegoTextField.gridy = 7;
		getSearchFieldPanel().add(videojuegoTextField, gbc_videojuegoTextField);
		
		JLabel idUsuarioLbl = new JLabel("ID Usuario:");
		GridBagConstraints gbc_idUsuarioLbl = new GridBagConstraints();
		gbc_idUsuarioLbl.anchor = GridBagConstraints.WEST;
		gbc_idUsuarioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idUsuarioLbl.gridx = 5;
		gbc_idUsuarioLbl.gridy = 7;
		getSearchFieldPanel().add(idUsuarioLbl, gbc_idUsuarioLbl);
		
		idUsuarioSpinner = new JSpinner();
		GridBagConstraints gbc_idUsuarioSpinner = new GridBagConstraints();
		gbc_idUsuarioSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_idUsuarioSpinner.gridwidth = 2;
		gbc_idUsuarioSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idUsuarioSpinner.gridx = 6;
		gbc_idUsuarioSpinner.gridy = 7;
		getSearchFieldPanel().add(idUsuarioSpinner, gbc_idUsuarioSpinner);
		
		JLabel idEmpleadoLbl = new JLabel("ID Empleado:");
		GridBagConstraints gbc_idEmpleadoLbl = new GridBagConstraints();
		gbc_idEmpleadoLbl.anchor = GridBagConstraints.EAST;
		gbc_idEmpleadoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idEmpleadoLbl.gridx = 11;
		gbc_idEmpleadoLbl.gridy = 7;
		getSearchFieldPanel().add(idEmpleadoLbl, gbc_idEmpleadoLbl);
		
		idEmpleadoSpinner = new JSpinner();
		GridBagConstraints gbc_idEmpleadoSpinner = new GridBagConstraints();
		gbc_idEmpleadoSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_idEmpleadoSpinner.gridwidth = 2;
		gbc_idEmpleadoSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idEmpleadoSpinner.gridx = 12;
		gbc_idEmpleadoSpinner.gridy = 7;
		getSearchFieldPanel().add(idEmpleadoSpinner, gbc_idEmpleadoSpinner);
		
		JLabel estadoAnuncioLbl = new JLabel("Estado Anuncio:");
		GridBagConstraints gbc_estadoAnuncioLbl = new GridBagConstraints();
		gbc_estadoAnuncioLbl.anchor = GridBagConstraints.WEST;
		gbc_estadoAnuncioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_estadoAnuncioLbl.gridx = 1;
		gbc_estadoAnuncioLbl.gridy = 8;
		getSearchFieldPanel().add(estadoAnuncioLbl, gbc_estadoAnuncioLbl);
		
		JPanel estadoRadioField = new JPanel();
		GridBagConstraints gbc_estadoRadioField = new GridBagConstraints();
		gbc_estadoRadioField.gridheight = 3;
		gbc_estadoRadioField.anchor = GridBagConstraints.NORTHWEST;
		gbc_estadoRadioField.insets = new Insets(0, 0, 0, 5);
		gbc_estadoRadioField.gridwidth = 2;
		gbc_estadoRadioField.gridx = 2;
		gbc_estadoRadioField.gridy = 8;
		getSearchFieldPanel().add(estadoRadioField, gbc_estadoRadioField);
		GridBagLayout gbl_estadoRadioField = new GridBagLayout();
		gbl_estadoRadioField.columnWidths = new int[]{0, 0};
		gbl_estadoRadioField.rowHeights = new int[]{0, 0, 0, 0};
		gbl_estadoRadioField.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_estadoRadioField.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		estadoRadioField.setLayout(gbl_estadoRadioField);
		
		JRadioButton estadoActivoRadioBtn = new JRadioButton("Activo");
		estadoActivoRadioBtn.setActionCommand("1");
		GridBagConstraints gbc_estadoActivoRadioBtn = new GridBagConstraints();
		gbc_estadoActivoRadioBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoActivoRadioBtn.insets = new Insets(0, 0, 5, 0);
		gbc_estadoActivoRadioBtn.gridx = 0;
		gbc_estadoActivoRadioBtn.gridy = 0;
		estadoRadioField.add(estadoActivoRadioBtn, gbc_estadoActivoRadioBtn);
		
		JRadioButton estadoPendienteRadioBtn = new JRadioButton("Pendiente");
		estadoPendienteRadioBtn.setActionCommand("3");
		GridBagConstraints gbc_estadoPendienteRadioBtn = new GridBagConstraints();
		gbc_estadoPendienteRadioBtn.insets = new Insets(0, 0, 5, 0);
		gbc_estadoPendienteRadioBtn.gridx = 0;
		gbc_estadoPendienteRadioBtn.gridy = 1;
		estadoRadioField.add(estadoPendienteRadioBtn, gbc_estadoPendienteRadioBtn);
		
		JRadioButton estadoFinalizadoRadioBtn = new JRadioButton("Finalizado");
		estadoFinalizadoRadioBtn.setActionCommand("2");
		GridBagConstraints gbc_estadoFinalizadoRadioBtn = new GridBagConstraints();
		gbc_estadoFinalizadoRadioBtn.gridx = 0;
		gbc_estadoFinalizadoRadioBtn.gridy = 2;
		estadoRadioField.add(estadoFinalizadoRadioBtn, gbc_estadoFinalizadoRadioBtn);
		
		estadoButtonGroup.add(estadoActivoRadioBtn);
		estadoActivoRadioBtn.setActionCommand("1");
		estadoButtonGroup.add(estadoPendienteRadioBtn);
		estadoPendienteRadioBtn.setActionCommand("3");
		estadoButtonGroup.add(estadoFinalizadoRadioBtn);
		estadoFinalizadoRadioBtn.setActionCommand("2");
		
		JButton buscarAnuncioButton = new JButton();
		buscarAnuncioButton.setAction(new PaginacionInicialAction(this, "Buscar"));
		GridBagConstraints gbc_buscarAnuncioButton = new GridBagConstraints();
		gbc_buscarAnuncioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscarAnuncioButton.gridwidth = 2;
		gbc_buscarAnuncioButton.gridx = 16;
		gbc_buscarAnuncioButton.gridy = 10;
		getSearchFieldPanel().add(buscarAnuncioButton, gbc_buscarAnuncioButton);
		postInitialize();
	}

	public void postInitialize() {
		try {
			generoComboBox.setRenderer(new GeneroListCellRenderer());
			plataformaComboBox.setRenderer(new PlataformaListCellRenderer());
			idiomaComboBox.setRenderer(new IdiomaListCellRenderer());
			SwingUtils.changeDateChooserColor(fechaFinDateChooser, Color.WHITE);
			SwingUtils.changeDateChooserColor(fechaInicioDateChooser, Color.WHITE);
			getTableResults().setDefaultRenderer(Object.class, new AnuncioTableCellRenderer());
		} catch(Exception ex) {
			logger.error(ex);
		}
	}
	
	public void setModel(DefaultComboBoxModel<Genero>generoModel, DefaultComboBoxModel<Idioma>idiomaModel, 
					DefaultComboBoxModel<Plataforma>plataformaModel) {
		generoComboBox.setModel(generoModel);
		plataformaComboBox.setModel(plataformaModel);
		idiomaComboBox.setModel(idiomaModel);
		idiomaComboBox.setSelectedIndex(6);
	}
	
	protected Integer getSelectedEstado() {
		Integer estadoId = null;
		AbstractButton rb = null;
		for(Enumeration<AbstractButton> rbs = estadoButtonGroup.getElements(); rbs.hasMoreElements();) {
			rb = rbs.nextElement();
			if(rb.isSelected()) {
				estadoId = Integer.valueOf(rb.getActionCommand());  
			}
		}
		return estadoId;
	}
	
	public AnuncioCriteria getCriteria() {
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombre(tituloTextField.getText().trim());
		Date fechaInicio = fechaInicioDateChooser.getDate();
		criteria.setFechaInicio(fechaInicio);
		Idioma i = (Idioma)idiomaComboBox.getSelectedItem();
		criteria.setIdIdiomaVideojuego(i.getId());
		criteria.setPrecioDesde((double)precioDesdeSlider.getValue());
		criteria.setPrecioHasta((double)precioHastaSlider.getValue());
		logger.info("Buscando anuncios por "+criteria);
		Integer estadoId = getSelectedEstado();
		
		criteria.setIdEstadoAnuncio(estadoId);
		if((Integer)idEmpleadoSpinner.getValue()!=0) {
			criteria.setIdEmpleado(Long.valueOf((Integer)idEmpleadoSpinner.getValue()));
		}
		if((Integer)idUsuarioSpinner.getValue()!=0) {
			criteria.setIdUsuario(Long.valueOf((Integer)idUsuarioSpinner.getValue()));
		}
		return criteria;
	}

	@Override
	protected void addButtonsColumn() {
		ButtonColumn detailButton = new ButtonColumn(getTableResults(), new OpenAnuncioDetailAction(this), 4, 
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1431_editors_editors_package_package.png")));
		ButtonColumn deleteColumn = new ButtonColumn(getTableResults(), new SoftDeleteAnuncioAction(this), 5,
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1815_no_no.png")));
	}
	
}

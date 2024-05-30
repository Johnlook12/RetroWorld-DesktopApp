package com.pinguela.retroworld.ui.desktop.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.OpenVideojuegoDetailAction;
import com.pinguela.retroworld.ui.desktop.controller.PagedSearchAction;
import com.pinguela.retroworld.ui.desktop.controller.VideojuegoPagedSearchAction;
import com.pinguela.retroworld.ui.desktop.renderer.ButtonColumn;
import com.pinguela.retroworld.ui.desktop.renderer.DesarrolladoraListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.VideojuegoTableCellRenderer;
import com.toedter.calendar.JDateChooser;

public class VideojuegoSearchView extends PaginatedSearchView<Videojuego> {
	
	private static Logger logger = LogManager.getLogger(VideojuegoSearchView.class);
	private JDateChooser fechaLanzamientoDesdeDateChooser;
	private JDateChooser fechaLanzamientoHastaDateChooser;
	private JComboBox<Idioma> idiomaComboBox;
	private JComboBox<Plataforma> plataformaComboBox;
	private JComboBox <Genero>generoComboBox;
	private JTextField idTextField;
	private JComboBox<Desarrolladora> desarrolladoraComboBox;

	public VideojuegoSearchView() {
		initialize();
		postInitialize();
	}
	
	private void initialize() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 30, 54, 15, 86, 93, 70, 81, 70, 68, 29, 41, 86, 0, 0, 0, 57, 0};
		gridBagLayout.rowHeights = new int[]{22, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getSearchFieldPanel().setLayout(gridBagLayout);
		
		JLabel videojuegoLbl = new JLabel("Buscar Videojuegos");
		videojuegoLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_videojuegoLbl = new GridBagConstraints();
		gbc_videojuegoLbl.gridwidth = 3;
		gbc_videojuegoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoLbl.gridx = 4;
		gbc_videojuegoLbl.gridy = 1;
		getSearchFieldPanel().add(videojuegoLbl, gbc_videojuegoLbl);
		
		JLabel idLbl = new JLabel("ID:");
		GridBagConstraints gbc_idLbl = new GridBagConstraints();
		gbc_idLbl.anchor = GridBagConstraints.EAST;
		gbc_idLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idLbl.gridx = 5;
		gbc_idLbl.gridy = 3;
		getSearchFieldPanel().add(idLbl, gbc_idLbl);
		
		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 6;
		gbc_idTextField.gridy = 3;
		getSearchFieldPanel().add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		
		JLabel fechaLanzamientoDesdeLbl = new JLabel("Fecha lanzamiento:");
		GridBagConstraints gbc_fechaLanzamientoDesdeLbl = new GridBagConstraints();
		gbc_fechaLanzamientoDesdeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoDesdeLbl.gridx = 8;
		gbc_fechaLanzamientoDesdeLbl.gridy = 3;
		getSearchFieldPanel().add(fechaLanzamientoDesdeLbl, gbc_fechaLanzamientoDesdeLbl);
		
		fechaLanzamientoDesdeDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaLanzamientoDesdeDateChooser = new GridBagConstraints();
		gbc_fechaLanzamientoDesdeDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoDesdeDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaLanzamientoDesdeDateChooser.gridx = 9;
		gbc_fechaLanzamientoDesdeDateChooser.gridy = 3;
		getSearchFieldPanel().add(fechaLanzamientoDesdeDateChooser, gbc_fechaLanzamientoDesdeDateChooser);
		
		JLabel fechaSeparatorLbl = new JLabel("");
		fechaSeparatorLbl.setIcon(new ImageIcon(VideojuegoSearchView.class.getResource("/icons/icons8-horizontal-line-16.png")));
		GridBagConstraints gbc_fechaSeparatorLbl = new GridBagConstraints();
		gbc_fechaSeparatorLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaSeparatorLbl.gridx = 10;
		gbc_fechaSeparatorLbl.gridy = 3;
		getSearchFieldPanel().add(fechaSeparatorLbl, gbc_fechaSeparatorLbl);
		
		fechaLanzamientoHastaDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaLanzamientoHastaDateChooser = new GridBagConstraints();
		gbc_fechaLanzamientoHastaDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoHastaDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaLanzamientoHastaDateChooser.gridx = 11;
		gbc_fechaLanzamientoHastaDateChooser.gridy = 3;
		getSearchFieldPanel().add(fechaLanzamientoHastaDateChooser, gbc_fechaLanzamientoHastaDateChooser);
		
		JLabel idiomaLbl = new JLabel("Idioma:");
		GridBagConstraints gbc_idiomaLbl = new GridBagConstraints();
		gbc_idiomaLbl.anchor = GridBagConstraints.EAST;
		gbc_idiomaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaLbl.gridx = 13;
		gbc_idiomaLbl.gridy = 3;
		getSearchFieldPanel().add(idiomaLbl, gbc_idiomaLbl);
		
		idiomaComboBox = new JComboBox();
		GridBagConstraints gbc_idiomaComboBox = new GridBagConstraints();
		gbc_idiomaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomaComboBox.gridx = 14;
		gbc_idiomaComboBox.gridy = 3;
		getSearchFieldPanel().add(idiomaComboBox, gbc_idiomaComboBox);
		
		JLabel nameLbl = new JLabel("Nombre:");
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 5;
		gbc_nameLbl.gridy = 5;
		getSearchFieldPanel().add(nameLbl, gbc_nameLbl);
		
		JTextField nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 6;
		gbc_nameTextField.gridy = 5;
		getSearchFieldPanel().add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);
		
		JLabel generoLbl = new JLabel("Genero:");
		GridBagConstraints gbc_generoLbl = new GridBagConstraints();
		gbc_generoLbl.anchor = GridBagConstraints.EAST;
		gbc_generoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_generoLbl.gridx = 8;
		gbc_generoLbl.gridy = 5;
		getSearchFieldPanel().add(generoLbl, gbc_generoLbl);
		
		generoComboBox = new JComboBox();
		GridBagConstraints gbc_generoComboBox = new GridBagConstraints();
		gbc_generoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoComboBox.gridx = 9;
		gbc_generoComboBox.gridy = 5;
		getSearchFieldPanel().add(generoComboBox, gbc_generoComboBox);
		
		JLabel plataformaLbl = new JLabel("Plataforma:");
		GridBagConstraints gbc_plataformaLbl = new GridBagConstraints();
		gbc_plataformaLbl.anchor = GridBagConstraints.EAST;
		gbc_plataformaLbl.insets = new Insets(0, 0, 5, 5);
		gbc_plataformaLbl.gridx = 13;
		gbc_plataformaLbl.gridy = 5;
		getSearchFieldPanel().add(plataformaLbl, gbc_plataformaLbl);
		
		plataformaComboBox = new JComboBox();
		GridBagConstraints gbc_plataformaComboBox = new GridBagConstraints();
		gbc_plataformaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_plataformaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_plataformaComboBox.gridx = 14;
		gbc_plataformaComboBox.gridy = 5;
		getSearchFieldPanel().add(plataformaComboBox, gbc_plataformaComboBox);
		
		JLabel desarrolladoraLbl = new JLabel("Desarrolladora:");
		GridBagConstraints gbc_desarrolladoraLbl = new GridBagConstraints();
		gbc_desarrolladoraLbl.anchor = GridBagConstraints.EAST;
		gbc_desarrolladoraLbl.insets = new Insets(0, 0, 5, 5);
		gbc_desarrolladoraLbl.gridx = 5;
		gbc_desarrolladoraLbl.gridy = 7;
		getSearchFieldPanel().add(desarrolladoraLbl, gbc_desarrolladoraLbl);
		
		desarrolladoraComboBox = new JComboBox();
		GridBagConstraints gbc_desarrolladoraComboBox = new GridBagConstraints();
		gbc_desarrolladoraComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_desarrolladoraComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_desarrolladoraComboBox.gridx = 6;
		gbc_desarrolladoraComboBox.gridy = 7;
		getSearchFieldPanel().add(desarrolladoraComboBox, gbc_desarrolladoraComboBox);
		JButton buscarVideojuegoButton = new JButton("");
		buscarVideojuegoButton.setAction(new VideojuegoPagedSearchAction(PagedSearchAction.START, this, "",
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1467_xmag_xmag.png"))));
		GridBagConstraints gbc_buscarVideojuegoButton = new GridBagConstraints();
		gbc_buscarVideojuegoButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarVideojuegoButton.gridx = 14;
		gbc_buscarVideojuegoButton.gridy = 8;
		getSearchFieldPanel().add(buscarVideojuegoButton, gbc_buscarVideojuegoButton);
	}
	
	public void postInitialize() {
		getTableResults().setDefaultRenderer(Object.class, new VideojuegoTableCellRenderer());
		idiomaComboBox.setRenderer(new IdiomaListCellRenderer());
		plataformaComboBox.setRenderer(new PlataformaListCellRenderer());
		generoComboBox.setRenderer(new GeneroListCellRenderer());
		desarrolladoraComboBox.setRenderer(new DesarrolladoraListCellRenderer());
	}

	@Override
	public AbstractCriteria getCriteria() {
		VideojuegoCriteria criteria= new VideojuegoCriteria();
		criteria.setFechaLanzamientoDesde(fechaLanzamientoDesdeDateChooser.getDate());
		criteria.setFechaLanzamientoHasta(fechaLanzamientoHastaDateChooser.getDate());
		Idioma i = (Idioma) idiomaComboBox.getSelectedItem();
		Genero g = (Genero) generoComboBox.getSelectedItem();
		Plataforma p = (Plataforma) plataformaComboBox.getSelectedItem();
		Desarrolladora d = (Desarrolladora) desarrolladoraComboBox.getSelectedItem();	
//		criteria.setIdIdioma(i.getId());
//		criteria.setIdGenero(g.getId());
//		criteria.setIdPlataforma(p.getId());
//		criteria.setIdDesarrolladora(d.getId());
//		criteria.setId(Integer.valueOf(idTextField.getText().trim()));
		return criteria;
	}
	
	public void setModel(DefaultComboBoxModel<Idioma> idiomaModel, DefaultComboBoxModel<Plataforma> plataformaModel,
			DefaultComboBoxModel<Genero> generoModel, DefaultComboBoxModel<Desarrolladora> desarrolladoraModel) {
		idiomaComboBox.setModel(idiomaModel);
		generoComboBox.setModel(generoModel);
		plataformaComboBox.setModel(plataformaModel);
		desarrolladoraComboBox.setModel(desarrolladoraModel);
	}

	@Override
	public void addButtonsColumn() {
		ButtonColumn detailButton = new ButtonColumn(getTableResults(), new OpenVideojuegoDetailAction(this), 4, new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1431_editors_editors_package_package.png")));
	}
}

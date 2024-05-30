package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.ui.desktop.controller.AsignarGeneroAction;
import com.pinguela.retroworld.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

public class AsignarGeneroDialog extends RWDialog{
	public static final String CANCEL_ASIGNAR_PROPERTY = "cancelAsignarGenero";
	private static Logger logger = LogManager.getLogger(AsignarGeneroDialog.class);

	DefaultListModel generoModel;
	private Videojuego videojuego;
	List<Genero> selectedGeneros;

	private final JPanel contentPanel = new JPanel();
	private JComboBox generoComboBox;
	private JButton addGeneroButton;
	private JButton removeGeneroButton;
	private JList generoList;
	private JPanel addedGeneroPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AsignarGeneroDialog dialog = new AsignarGeneroDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public AsignarGeneroDialog() {
		setBounds(100, 100, 565, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		addedGeneroPane = new JPanel();
		contentPanel.add(addedGeneroPane, BorderLayout.EAST);
		GridBagLayout gbl_addedGeneroPane = new GridBagLayout();
		gbl_addedGeneroPane.columnWidths = new int[]{0, 0, 8, 75, 0};
		gbl_addedGeneroPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_addedGeneroPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_addedGeneroPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		addedGeneroPane.setLayout(gbl_addedGeneroPane);


		generoList = new JList();
		GridBagConstraints gbc_generoList = new GridBagConstraints();
		gbc_generoList.insets = new Insets(0, 0, 5, 5);
		gbc_generoList.fill = GridBagConstraints.BOTH;
		gbc_generoList.gridx = 1;
		gbc_generoList.gridy = 0;
		addedGeneroPane.add(generoList, gbc_generoList);

		JSeparator generoSeparator = new JSeparator();
		generoSeparator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_generoSeparator = new GridBagConstraints();
		gbc_generoSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_generoSeparator.gridx = 0;
		gbc_generoSeparator.gridy = 1;
		addedGeneroPane.add(generoSeparator, gbc_generoSeparator);

		removeGeneroButton = new JButton("");
		removeGeneroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSelectedGenero();
			}
		});
		removeGeneroButton.setIcon(new ImageIcon(AsignarGeneroDialog.class.getResource("/icons/icons8-back-arrow-32.png")));
		GridBagConstraints gbc_removeGeneroButton = new GridBagConstraints();
		gbc_removeGeneroButton.gridx = 3;
		gbc_removeGeneroButton.gridy = 2;
		addedGeneroPane.add(removeGeneroButton, gbc_removeGeneroButton);

		JPanel selectGeneroPane = new JPanel();
		contentPanel.add(selectGeneroPane, BorderLayout.CENTER);
		GridBagLayout gbl_selectGeneroPane = new GridBagLayout();
		gbl_selectGeneroPane.columnWidths = new int[]{0, 0, 0};
		gbl_selectGeneroPane.rowHeights = new int[]{46, 50, 0, 0, 0, 0};
		gbl_selectGeneroPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_selectGeneroPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		selectGeneroPane.setLayout(gbl_selectGeneroPane);

		generoComboBox = new JComboBox();
		GridBagConstraints gbc_generoComboBox = new GridBagConstraints();
		gbc_generoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoComboBox.gridx = 0;
		gbc_generoComboBox.gridy = 2;
		selectGeneroPane.add(generoComboBox, gbc_generoComboBox);

		addGeneroButton = new JButton("");
		addGeneroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSelectedGenero();
			}
		});
		addGeneroButton.setIcon(new ImageIcon(AsignarGeneroDialog.class.getResource("/icons/icons8-forward-button-32.png")));
		GridBagConstraints gbc_addGeneroButton = new GridBagConstraints();
		gbc_addGeneroButton.insets = new Insets(0, 0, 5, 0);
		gbc_addGeneroButton.gridx = 1;
		gbc_addGeneroButton.gridy = 2;
		selectGeneroPane.add(addGeneroButton, gbc_addGeneroButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addPropertyChangeListener(new PropertyChangeListener() {	
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY, null, selectedGeneros);
			}
		});
		okButton.setAction(new AsignarGeneroAction(this,"OK"));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(AsignarGeneroDialog.CANCEL_ASIGNAR_PROPERTY, null, null);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		postInitialize();
	}

	private void postInitialize() {
		selectedGeneros = new ArrayList<Genero>();
		MatteBorder leftBorder = new MatteBorder(0, 2, 0, 0, Color.GRAY);
		generoList.setCellRenderer(new GeneroListCellRenderer());
		addedGeneroPane.setBorder(leftBorder);
		generoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		generoModel = new DefaultListModel<Genero>();

	}

	private void addSelectedGenero() {
		Genero genero= (Genero)generoComboBox.getSelectedItem();
		generoModel = new DefaultListModel<Genero>();
		boolean isNewGenero=true;

		for(Genero g:selectedGeneros) {
			if(g.getId()==genero.getId()) {
				isNewGenero=false;
				break;
			}
		}	
		if(isNewGenero) {
			selectedGeneros.add(genero);			
		}

		addListToModel(selectedGeneros);
	}

	public void addListToModel(List<Genero>generos) {
		for(Genero g:generos) {
			if(!generoModel.contains(g)) {
				generoModel.addElement(g);					
			}
		}
		setGeneroListModel(generoModel);
	}

	private void setGeneroListModel(DefaultListModel model) {
		generoList.setModel(model);
		generoList.clearSelection();
	}

	private void removeSelectedGenero() {
		selectedGeneros.remove((Genero)generoList.getSelectedValue());
		DefaultListModel<Genero>generoModel = new DefaultListModel<Genero>();
		for(Genero g:selectedGeneros) {
			generoModel.addElement(g);	
		}
		setGeneroListModel(generoModel);
	}

	public void setModel(DefaultComboBoxModel<Genero> model) {
		generoComboBox.setModel(model);
	}

	public void setGeneroComboBoxRenderer(GeneroListCellRenderer renderer) {
		generoComboBox.setRenderer(renderer);
	}

	public Genero getSelectedGenero() {
		return (Genero)generoComboBox.getSelectedItem();
	}

	public void setSelectedGeneros(List<Genero>generos) {
		this.selectedGeneros = generos;
	}

	public List<Genero> getSelectedGeneros(){
		return this.selectedGeneros;
	}

	public void setVideojuego(Videojuego v) {
		this.videojuego=v;
	}

	public Long getIdVideojuego() {
		return videojuego.getId();
	}
}

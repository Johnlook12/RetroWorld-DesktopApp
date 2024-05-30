package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.ui.desktop.controller.AsignarIdiomaAction;
import com.pinguela.retroworld.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class AsignarIdiomaDialog extends RWDialog{
	public static final String CANCEL_ASIGNAR_PROPERTY = "cancelAsignarIdioma";
	private static Logger logger = LogManager.getLogger(AsignarIdiomaDialog.class);
	private static final long serialVersionUID = 1L;

	private List<Idioma> selectedIdiomas;
	private Videojuego videojuego;
	DefaultListModel<Idioma> idiomaModel;

	private final JPanel contentPanel = new JPanel();
	private JComboBox idiomaComboBox;
	private JButton addIdiomaButton;
	private JPanel addedIdiomasPane;
	private JList idiomaList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AsignarIdiomaDialog dialog = new AsignarIdiomaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public AsignarIdiomaDialog() {
		setBounds(100, 100, 565, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		addedIdiomasPane = new JPanel();
		contentPanel.add(addedIdiomasPane, BorderLayout.EAST);
		GridBagLayout gbl_addedIdiomasPane = new GridBagLayout();
		gbl_addedIdiomasPane.columnWidths = new int[]{15, 15, 75, 0};
		gbl_addedIdiomasPane.rowHeights = new int[]{0, 9, 35, 0, 0};
		gbl_addedIdiomasPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_addedIdiomasPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		addedIdiomasPane.setLayout(gbl_addedIdiomasPane);

		idiomaList = new JList();
		GridBagConstraints gbc_idiomaList = new GridBagConstraints();
		gbc_idiomaList.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaList.fill = GridBagConstraints.BOTH;
		gbc_idiomaList.gridx = 0;
		gbc_idiomaList.gridy = 0;
		addedIdiomasPane.add(idiomaList, gbc_idiomaList);

		JButton removeIdiomaButton = new JButton("");
		removeIdiomaButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelectedIdioma();
			}
		});
		removeIdiomaButton.setIcon(new ImageIcon(AsignarIdiomaDialog.class.getResource("/icons/icons8-back-arrow-32.png")));
		GridBagConstraints gbc_removeIdiomaButton = new GridBagConstraints();
		gbc_removeIdiomaButton.gridx = 2;
		gbc_removeIdiomaButton.gridy = 3;
		addedIdiomasPane.add(removeIdiomaButton, gbc_removeIdiomaButton);

		JPanel selectIdiomaPane = new JPanel();
		contentPanel.add(selectIdiomaPane, BorderLayout.CENTER);
		GridBagLayout gbl_selectIdiomaPane = new GridBagLayout();
		gbl_selectIdiomaPane.columnWidths = new int[]{0, 0, 0};
		gbl_selectIdiomaPane.rowHeights = new int[]{46, 50, 0, 0, 0, 0};
		gbl_selectIdiomaPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_selectIdiomaPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		selectIdiomaPane.setLayout(gbl_selectIdiomaPane);

		idiomaComboBox = new JComboBox();
		GridBagConstraints gbc_idiomaComboBox = new GridBagConstraints();
		gbc_idiomaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomaComboBox.gridx = 0;
		gbc_idiomaComboBox.gridy = 2;
		selectIdiomaPane.add(idiomaComboBox, gbc_idiomaComboBox);


		addIdiomaButton = new JButton("");
		addIdiomaButton.setIcon(new ImageIcon(AsignarIdiomaDialog.class.getResource("/icons/icons8-forward-button-32.png")));
		addIdiomaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSelectedIdioma();
			}
		});
		GridBagConstraints gbc_addIdiomaButton = new GridBagConstraints();
		gbc_addIdiomaButton.insets = new Insets(0, 0, 5, 0);
		gbc_addIdiomaButton.gridx = 1;
		gbc_addIdiomaButton.gridy = 2;
		selectIdiomaPane.add(addIdiomaButton, gbc_addIdiomaButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton();
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY, null, selectedIdiomas);
			}
		});
		okButton.setAction(new AsignarIdiomaAction(this, "OK"));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(AsignarIdiomaDialog.CANCEL_ASIGNAR_PROPERTY, null, null);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		postInitialize();

	}

	private void postInitialize() {
		selectedIdiomas = new ArrayList<Idioma>();
		idiomaList.setCellRenderer(new IdiomaListCellRenderer());
		MatteBorder leftBorder = new MatteBorder(0, 2, 0, 0, Color.GRAY);
		addedIdiomasPane.setBorder(leftBorder);
		idiomaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		idiomaModel = new DefaultListModel<Idioma>();
	}

	private void addSelectedIdioma() {
		Idioma idioma = (Idioma)idiomaComboBox.getSelectedItem();
		idiomaModel = new DefaultListModel<Idioma>();
		logger.info(idioma);
		boolean isNewIdioma=true;

		for(Idioma i:selectedIdiomas) {
			if(i.getId()==idioma.getId()) {
				isNewIdioma=false;
				break;
			}
		}	
		if(isNewIdioma) {
			selectedIdiomas.add(idioma);			
		}

		addListToModel(selectedIdiomas);
	}

	public void addListToModel(List<Idioma>idiomas) {
		for(Idioma i:idiomas) {
			if(!idiomaModel.contains(i)) {
				idiomaModel.addElement(i);					
			}
		}
		setIdiomaListModel(idiomaModel);
	}

	private void setIdiomaListModel(DefaultListModel model) {
		idiomaList.setModel(model);
		idiomaList.clearSelection();
	}

	private void removeSelectedIdioma() {
		selectedIdiomas.remove((Idioma)idiomaList.getSelectedValue());
		DefaultListModel<Idioma>idiomaModel = new DefaultListModel<Idioma>();
		for(Idioma i:selectedIdiomas) {
			idiomaModel.addElement(i);	
		}
		setIdiomaListModel(idiomaModel);
	}

	public void setModel(DefaultComboBoxModel<Idioma> model) {
		idiomaComboBox.setModel(model);
	}

	public void setIdiomaComboBoxRenderer(IdiomaListCellRenderer renderer) {
		idiomaComboBox.setRenderer(renderer);
	}

	public Idioma getSelectedIdioma() {
		return (Idioma)idiomaComboBox.getSelectedItem();
	}

	public void setSelectedIdiomas(List<Idioma>idiomas) {
		this.selectedIdiomas = idiomas;
	}

	public List<Idioma> getSelectedIdiomas(){
		return this.selectedIdiomas;
	}

	public void setVideojuego(Videojuego v) {
		this.videojuego=v;
	}

	public Long getIdVideojuego() {
		return videojuego.getId();
	}
}

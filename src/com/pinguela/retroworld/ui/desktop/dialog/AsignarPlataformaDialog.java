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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.ui.desktop.controller.AsignarPlataformaAction;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

import javax.swing.ImageIcon;

public class AsignarPlataformaDialog extends RWDialog{
	public static final String CANCEL_ASIGNAR_PROPERTY = "cancelAsignarPlataforma";

	DefaultListModel plataformaModel;
	private Videojuego videojuego;
	List<Plataforma> selectedPlataformas;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel addedPlataformaPane;
	private JList plataformaList;
	private JComboBox plataformaComboBox;
	private JButton addPlataformaButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AsignarPlataformaDialog dialog = new AsignarPlataformaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AsignarPlataformaDialog() {
		setBounds(100, 100, 565, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		addedPlataformaPane = new JPanel();
		contentPanel.add(addedPlataformaPane, BorderLayout.EAST);
		GridBagLayout gbl_addedPlataformaPane = new GridBagLayout();
		gbl_addedPlataformaPane.columnWidths = new int[]{0, 0, 8, 75, 0};
		gbl_addedPlataformaPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_addedPlataformaPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_addedPlataformaPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		addedPlataformaPane.setLayout(gbl_addedPlataformaPane);

		plataformaList = new JList();
		GridBagConstraints gbc_plataformaList = new GridBagConstraints();
		gbc_plataformaList.insets = new Insets(0, 0, 5, 5);
		gbc_plataformaList.fill = GridBagConstraints.BOTH;
		gbc_plataformaList.gridx = 1;
		gbc_plataformaList.gridy = 0;
		addedPlataformaPane.add(plataformaList, gbc_plataformaList);
		
		JButton removePlataformaButton = new JButton("");
		removePlataformaButton.setIcon(new ImageIcon(AsignarPlataformaDialog.class.getResource("/icons/icons8-back-arrow-32.png")));
		removePlataformaButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelectedPlataforma();	
			}
		});
		GridBagConstraints gbc_removePlataformaButton = new GridBagConstraints();
		gbc_removePlataformaButton.gridx = 3;
		gbc_removePlataformaButton.gridy = 2;
		addedPlataformaPane.add(removePlataformaButton, gbc_removePlataformaButton);

		JPanel selectPlataformaPane = new JPanel();
		contentPanel.add(selectPlataformaPane, BorderLayout.CENTER);
		GridBagLayout gbl_selectPlataformaPane = new GridBagLayout();
		gbl_selectPlataformaPane.columnWidths = new int[]{0, 0, 0};
		gbl_selectPlataformaPane.rowHeights = new int[]{46, 50, 0, 0, 0, 0};
		gbl_selectPlataformaPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_selectPlataformaPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		selectPlataformaPane.setLayout(gbl_selectPlataformaPane);

		plataformaComboBox = new JComboBox();
		GridBagConstraints gbc_plataformaComboBox = new GridBagConstraints();
		gbc_plataformaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_plataformaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_plataformaComboBox.gridx = 0;
		gbc_plataformaComboBox.gridy = 2;
		selectPlataformaPane.add(plataformaComboBox, gbc_plataformaComboBox);

		addPlataformaButton = new JButton("");
		addPlataformaButton.setIcon(new ImageIcon(AsignarPlataformaDialog.class.getResource("/icons/icons8-forward-button-32.png")));
		addPlataformaButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				addSelectedPlataforma();
			}
		});
		GridBagConstraints gbc_addPlataformaButton = new GridBagConstraints();
		gbc_addPlataformaButton.insets = new Insets(0, 0, 5, 0);
		gbc_addPlataformaButton.gridx = 1;
		gbc_addPlataformaButton.gridy = 2;
		selectPlataformaPane.add(addPlataformaButton, gbc_addPlataformaButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY, null, selectedPlataformas);
			}
		});
		okButton.setAction(new AsignarPlataformaAction(this));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(AsignarPlataformaDialog.CANCEL_ASIGNAR_PROPERTY, null, null);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		postInitialize();
	}

	private void postInitialize() {
		selectedPlataformas = new ArrayList<Plataforma>();
		MatteBorder leftBorder = new MatteBorder(0, 2, 0, 0, Color.GRAY);
		plataformaList.setCellRenderer(new PlataformaListCellRenderer());
		addedPlataformaPane.setBorder(leftBorder);
		plataformaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		plataformaModel = new DefaultListModel<Plataforma>();

	}

	private void addSelectedPlataforma() {
		Plataforma plataforma= (Plataforma)plataformaComboBox.getSelectedItem();
		plataformaModel = new DefaultListModel<Plataforma>();
		boolean isNewPlataforma=true;

		for(Plataforma p:selectedPlataformas) {
			if(p.getId()==plataforma.getId()) {
				isNewPlataforma=false;
				break;
			}
		}	
		if(isNewPlataforma) {
			selectedPlataformas.add(plataforma);			
		}

		addListToModel(selectedPlataformas);
	}

	public void addListToModel(List<Plataforma>plataformas) {
		for(Plataforma p:plataformas) {
			if(!plataformaModel.contains(p)) {
				plataformaModel.addElement(p);					
			}
		}
		setPlataformaListModel(plataformaModel);
	}

	private void setPlataformaListModel(DefaultListModel model) {
		plataformaList.setModel(model);
		plataformaList.clearSelection();
	}

	private void removeSelectedPlataforma() {
		selectedPlataformas.remove((Plataforma)plataformaList.getSelectedValue());
		DefaultListModel<Plataforma>plataformaModel = new DefaultListModel<Plataforma>();
		for(Plataforma p:selectedPlataformas) {
			plataformaModel.addElement(p);	
		}
		setPlataformaListModel(plataformaModel);
	}

	public void setModel(DefaultComboBoxModel<Plataforma> model) {
		plataformaComboBox.setModel(model);
	}

	public void setPlataformaComboBoxRenderer(PlataformaListCellRenderer renderer) {
		plataformaComboBox.setRenderer(renderer);
	}

	public Plataforma getSelectedPlataforma() {
		return (Plataforma)plataformaComboBox.getSelectedItem();
	}

	public void setSelectedPlataformas(List<Plataforma>plataformas) {
		this.selectedPlataformas = plataformas;
	}

	public List<Plataforma> getSelectedPlataformas(){
		return this.selectedPlataformas;
	}

	public void setVideojuego(Videojuego v) {
		this.videojuego=v;
	}

	public Long getIdVideojuego() {
		return videojuego.getId();
	}
}

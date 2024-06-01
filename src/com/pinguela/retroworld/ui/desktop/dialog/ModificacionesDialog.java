package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.pinguela.retroworld.ui.desktop.model.ModificacionTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.ModificacionTableCellRenderer;
import javax.swing.JScrollPane;

public class ModificacionesDialog extends RWDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel anuncioIdValueLbl;
	private JTable tableResults;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificacionesDialog dialog = new ModificacionesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificacionesDialog() {
		setBounds(100, 100, 751, 487);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		tableResults = new JTable();

		JPanel dataPanel = new JPanel();
		contentPanel.add(dataPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 36, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);

		JLabel anuncioTitleLbl = new JLabel("Anuncio:");
		anuncioTitleLbl.setFont(new Font("Arial", Font.BOLD, 17));
		GridBagConstraints gbc_anuncioTitleLbl = new GridBagConstraints();
		gbc_anuncioTitleLbl.anchor = GridBagConstraints.EAST;
		gbc_anuncioTitleLbl.insets = new Insets(0, 0, 5, 5);
		gbc_anuncioTitleLbl.gridx = 0;
		gbc_anuncioTitleLbl.gridy = 0;
		dataPanel.add(anuncioTitleLbl, gbc_anuncioTitleLbl);

		anuncioIdValueLbl = new JLabel("New label");
		anuncioIdValueLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_anuncioIdValueLbl = new GridBagConstraints();
		gbc_anuncioIdValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_anuncioIdValueLbl.gridx = 1;
		gbc_anuncioIdValueLbl.gridy = 0;
		dataPanel.add(anuncioIdValueLbl, gbc_anuncioIdValueLbl);

		JLabel modificacionesTitleLbl = new JLabel("Modificaciones");
		modificacionesTitleLbl.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_modificacionesTitleLbl = new GridBagConstraints();
		gbc_modificacionesTitleLbl.insets = new Insets(0, 0, 0, 5);
		gbc_modificacionesTitleLbl.gridx = 0;
		gbc_modificacionesTitleLbl.gridy = 2;
		dataPanel.add(modificacionesTitleLbl, gbc_modificacionesTitleLbl);
		
		JScrollPane resultsScrollPane = new JScrollPane();
		contentPanel.add(resultsScrollPane, BorderLayout.CENTER);
		resultsScrollPane.setViewportView(tableResults);
		
		postInitialize();
	}
	
	private void postInitialize() {
		tableResults.setDefaultRenderer(Object.class, new ModificacionTableCellRenderer());
	}
	
	public void setAnuncioId(Long id) {
		this.anuncioIdValueLbl.setText("#"+String.valueOf(id));
	}
	
	public void setModel(ModificacionTableModel model) {
		this.tableResults.setModel(model);
	}

}

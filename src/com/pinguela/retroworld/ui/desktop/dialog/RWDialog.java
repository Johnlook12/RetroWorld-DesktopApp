package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pinguela.retroworld.ui.desktop.view.AnuncioDetailView;

public class RWDialog extends JDialog {
	
	public static final String CLOSE_DIALOG_PROPERTY = "closeDialog";
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RWDialog dialog = new RWDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RWDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RWDialog.class.getResource("/icons/favicon.png")));
		setBounds(100, 100, 1050, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}

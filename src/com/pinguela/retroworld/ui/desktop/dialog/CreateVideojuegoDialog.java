package com.pinguela.retroworld.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.ui.desktop.controller.CreateVideojuegoAction;
import com.pinguela.retroworld.ui.desktop.controller.SelectVideojuegoImageAction;
import com.pinguela.retroworld.ui.desktop.renderer.DesarrolladoraListCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.FormatUtils;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;

public class CreateVideojuegoDialog extends RWDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JPanel imagesPane;
	private JScrollPane imagePreviewScrollPane;
	private JPanel imagePreviewPane;
	private JFileChooser imageFileChooser;
	private JTextArea descripcionTextArea;
	private JComboBox desarrolladoraComboBox;
	private JDateChooser fechaLanzamientoDateChooser;
	private List<JTextComponent> textFields;

	private Videojuego videojuego;
	private List<Desarrolladora> desarrolladoras;
	private List<File> imageFiles;

	public static void main(String[] args) {
		try {
			CreateVideojuegoDialog dialog = new CreateVideojuegoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateVideojuegoDialog() {
		setBounds(100, 100, 687, 609);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{67, 0, 0, 58, 58, 0};
		gbl_contentPanel.rowHeights = new int[]{40, 0, 36, 0, 0, 35, 0, 40, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		JLabel nombreLbl = new JLabel("Nombre:");
		GridBagConstraints gbc_nombreLbl = new GridBagConstraints();
		gbc_nombreLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLbl.anchor = GridBagConstraints.EAST;
		gbc_nombreLbl.gridx = 1;
		gbc_nombreLbl.gridy = 1;
		contentPanel.add(nombreLbl, gbc_nombreLbl);


		nombreTextField = new JTextField();
		GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
		gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTextField.gridx = 2;
		gbc_nombreTextField.gridy = 1;
		contentPanel.add(nombreTextField, gbc_nombreTextField);
		nombreTextField.setColumns(10);


		JLabel descripcionLbl = new JLabel("Descripción:");
		GridBagConstraints gbc_descripcionLbl = new GridBagConstraints();
		gbc_descripcionLbl.anchor = GridBagConstraints.EAST;
		gbc_descripcionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionLbl.gridx = 1;
		gbc_descripcionLbl.gridy = 3;
		contentPanel.add(descripcionLbl, gbc_descripcionLbl);


		descripcionTextArea = new JTextArea();
		GridBagConstraints gbc_descripcionTextArea = new GridBagConstraints();
		gbc_descripcionTextArea.gridwidth = 2;
		gbc_descripcionTextArea.gridheight = 3;
		gbc_descripcionTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descripcionTextArea.gridx = 2;
		gbc_descripcionTextArea.gridy = 3;
		contentPanel.add(descripcionTextArea, gbc_descripcionTextArea);


		JLabel desarrolladoraLbl = new JLabel("Desarrolladora:");
		GridBagConstraints gbc_desarrolladoraLbl = new GridBagConstraints();
		gbc_desarrolladoraLbl.anchor = GridBagConstraints.EAST;
		gbc_desarrolladoraLbl.insets = new Insets(0, 0, 5, 5);
		gbc_desarrolladoraLbl.gridx = 1;
		gbc_desarrolladoraLbl.gridy = 7;
		contentPanel.add(desarrolladoraLbl, gbc_desarrolladoraLbl);


		desarrolladoraComboBox = new JComboBox();
		GridBagConstraints gbc_desarrolladoraComboBox = new GridBagConstraints();
		gbc_desarrolladoraComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_desarrolladoraComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_desarrolladoraComboBox.gridx = 2;
		gbc_desarrolladoraComboBox.gridy = 7;
		contentPanel.add(desarrolladoraComboBox, gbc_desarrolladoraComboBox);


		JLabel fechaLanzamientoLbl = new JLabel("Fecha de lanzamiento:");
		GridBagConstraints gbc_fechaLanzamientoLbl = new GridBagConstraints();
		gbc_fechaLanzamientoLbl.insets = new Insets(0, 0, 0, 5);
		gbc_fechaLanzamientoLbl.gridx = 1;
		gbc_fechaLanzamientoLbl.gridy = 9;
		contentPanel.add(fechaLanzamientoLbl, gbc_fechaLanzamientoLbl);


		fechaLanzamientoDateChooser = new JDateChooser();
		fechaLanzamientoDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaLanzamientoDateChooser = new GridBagConstraints();
		gbc_fechaLanzamientoDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_fechaLanzamientoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaLanzamientoDateChooser.gridx = 2;
		gbc_fechaLanzamientoDateChooser.gridy = 9;
		contentPanel.add(fechaLanzamientoDateChooser, gbc_fechaLanzamientoDateChooser);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton crearButton = new JButton("Crear");
		crearButton.setAction(new CreateVideojuegoAction(this,"Crear"));
		crearButton.setActionCommand("OK");
		buttonPane.add(crearButton);
		getRootPane().setDefaultButton(crearButton);


		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(CLOSE_DIALOG_PROPERTY, null, null);
			}
		});
		cancelarButton.setActionCommand("Cancel");
		buttonPane.add(cancelarButton);



		JPanel titlePane = new JPanel();
		getContentPane().add(titlePane, BorderLayout.NORTH);

		JLabel addVideojuegoLbl = new JLabel("Añadir Videojuego");
		addVideojuegoLbl.setFont(new Font("Arial", Font.BOLD, 15));
		titlePane.add(addVideojuegoLbl);


		imagesPane = new JPanel();
		getContentPane().add(imagesPane, BorderLayout.EAST);
		GridBagLayout gbl_imagesPane = new GridBagLayout();
		gbl_imagesPane.columnWidths = new int[]{0, 115, 0, 0, 0, 0};
		gbl_imagesPane.rowHeights = new int[]{106, 116, 0, 0, 0};
		gbl_imagesPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_imagesPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		imagesPane.setLayout(gbl_imagesPane);

		imagePreviewScrollPane = new JScrollPane();
		GridBagConstraints gbc_imagePreviewScrollPane = new GridBagConstraints();
		gbc_imagePreviewScrollPane.gridheight = 2;
		gbc_imagePreviewScrollPane.gridwidth = 6;
		gbc_imagePreviewScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_imagePreviewScrollPane.fill = GridBagConstraints.BOTH;
		gbc_imagePreviewScrollPane.gridx = 0;
		gbc_imagePreviewScrollPane.gridy = 0;
		imagesPane.add(imagePreviewScrollPane, gbc_imagePreviewScrollPane);


		JButton addImageButton = new JButton();
		addImageButton.setAction(new SelectVideojuegoImageAction(this,"Añadir imagen", new ImageIcon(CreateVideojuegoDialog.class.getResource("/nuvola/16x16/1545_folder_photo_folder_photo.png"))));
		
		GridBagConstraints gbc_addImageButton = new GridBagConstraints();
		gbc_addImageButton.gridwidth = 3;
		gbc_addImageButton.insets = new Insets(0, 0, 5, 0);
		gbc_addImageButton.gridx = 3;
		gbc_addImageButton.gridy = 2;
		imagesPane.add(addImageButton, gbc_addImageButton);

		imagePreviewPane = new JPanel();
		imagePreviewScrollPane.setViewportView(imagePreviewPane);

		postInitialize();
	}

	private void postInitialize() {
		videojuego = new Videojuego();
		textFields = new ArrayList<JTextComponent>();
		imageFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png");
		imageFileChooser.setFileFilter(filter);
		imageFiles = new ArrayList<File>();
		descripcionTextArea.setLineWrap(true);
		descripcionTextArea.setWrapStyleWord(true);
		FormatUtils.setTextMaxChars(nombreTextField, 80);
		FormatUtils.setTextMaxChars(descripcionTextArea, 500);
		textFields.add(nombreTextField);
		textFields.add(descripcionTextArea);
		SwingUtils.changeDateChooserColor(fechaLanzamientoDateChooser, Color.WHITE);
	}


	public Videojuego getVideojuego() {
		videojuego.setNombre(nombreTextField.getText().trim());
		videojuego.setDescripcion(descripcionTextArea.getText().trim());
		videojuego.setFechaLanzamiento(fechaLanzamientoDateChooser.getDate());
		Desarrolladora desarrolladora = (Desarrolladora)desarrolladoraComboBox.getSelectedItem();
		videojuego.setIdDesarrolladora(desarrolladora.getId());
		return this.videojuego; 
	}
	
	public boolean validarTextFields() {
		for(JTextComponent field:textFields) {
			if(field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public void setVideojuego(Videojuego videojuego) {
		this.videojuego=videojuego;
	}

	public void setModel(DefaultComboBoxModel<Desarrolladora>model) {
		desarrolladoraComboBox.setModel(model);
	}
	
	public void setRenderer() {
		desarrolladoraComboBox.setRenderer(new DesarrolladoraListCellRenderer());		
	}

	public void setDesarrolladoras(List<Desarrolladora> desarrolladoras) {
		this.desarrolladoras=desarrolladoras;
	}

	public void setPreviewImageLbl(JLabel imageLbl) {
		this.imagePreviewPane.add(imageLbl);
		imagePreviewPane.repaint();
		imagePreviewPane.revalidate();
	}

	public JFileChooser getImageFileChooser() {
		return this.imageFileChooser;
	}

	public void addSelectedImage(File imageFile) {
		this.imageFiles.add(imageFile);
	}

	public List<File> getSelectedImages(){
		return this.imageFiles;
	}

	public Long getVideojuegoId() {
		return this.videojuego.getId();
	}
}

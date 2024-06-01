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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.EstadoAnuncio;
import com.pinguela.retroworld.model.EstadoVideojuego;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.CreateAnuncioAction;
import com.pinguela.retroworld.ui.desktop.controller.SelectAnuncioImageAction;
import com.pinguela.retroworld.ui.desktop.renderer.ComboBoxFilterDecorator;
import com.pinguela.retroworld.ui.desktop.renderer.EstadoVideojuegoListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.VideojuegoListCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.FormatUtils;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;

public class CreateAnuncioDialog extends RWDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tituloTextField;
	private JPanel imagesPane;
	private JScrollPane imagePreviewScrollPane;
	private JPanel imagePreviewPane;
	private JFileChooser imageFileChooser;
	private JTextArea descripcionTextArea;
	private JComboBox videojuegoComboBox;
	private JDateChooser fechaLanzamientoDateChooser;

	private Anuncio anuncio;
	private Results<Videojuego> videojuegos;
	private List<File> imageFiles;
	private JSpinner precioSpinner;
	private JComboBox estadoVideojuegoComboBox;
	private List<JTextComponent> textFields;

	public static void main(String[] args) {
		try {
			CreateAnuncioDialog dialog = new CreateAnuncioDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateAnuncioDialog() {
		setBounds(100, 100, 687, 609);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{67, 0, 0, 58, 58, 0};
		gbl_contentPanel.rowHeights = new int[]{40, 0, 36, 0, 0, 35, 0, 40, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		JLabel tituloLbl = new JLabel("Titulo:");
		GridBagConstraints gbc_tituloLbl = new GridBagConstraints();
		gbc_tituloLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLbl.anchor = GridBagConstraints.EAST;
		gbc_tituloLbl.gridx = 1;
		gbc_tituloLbl.gridy = 1;
		contentPanel.add(tituloLbl, gbc_tituloLbl);


		tituloTextField = new JTextField();
		GridBagConstraints gbc_tituloTextField = new GridBagConstraints();
		gbc_tituloTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloTextField.gridx = 2;
		gbc_tituloTextField.gridy = 1;
		contentPanel.add(tituloTextField, gbc_tituloTextField);
		tituloTextField.setColumns(10);


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


		JLabel videojuegoLbl = new JLabel("Videojuego:");
		GridBagConstraints gbc_videojuegoLbl = new GridBagConstraints();
		gbc_videojuegoLbl.anchor = GridBagConstraints.EAST;
		gbc_videojuegoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoLbl.gridx = 1;
		gbc_videojuegoLbl.gridy = 7;
		contentPanel.add(videojuegoLbl, gbc_videojuegoLbl);


		videojuegoComboBox = new JComboBox();
		GridBagConstraints gbc_videojuegoComboBox = new GridBagConstraints();
		gbc_videojuegoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_videojuegoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_videojuegoComboBox.gridx = 2;
		gbc_videojuegoComboBox.gridy = 7;
		contentPanel.add(videojuegoComboBox, gbc_videojuegoComboBox);


		JLabel fechaLanzamientoLbl = new JLabel("Fecha de lanzamiento:");
		GridBagConstraints gbc_fechaLanzamientoLbl = new GridBagConstraints();
		gbc_fechaLanzamientoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoLbl.gridx = 1;
		gbc_fechaLanzamientoLbl.gridy = 9;
		contentPanel.add(fechaLanzamientoLbl, gbc_fechaLanzamientoLbl);


		fechaLanzamientoDateChooser = new JDateChooser();
		fechaLanzamientoDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaLanzamientoDateChooser = new GridBagConstraints();
		gbc_fechaLanzamientoDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaLanzamientoDateChooser.gridx = 2;
		gbc_fechaLanzamientoDateChooser.gridy = 9;
		contentPanel.add(fechaLanzamientoDateChooser, gbc_fechaLanzamientoDateChooser);
		
		JLabel estadoVideojuegoLbl = new JLabel("Estado:");
		GridBagConstraints gbc_estadoVideojuegoLbl = new GridBagConstraints();
		gbc_estadoVideojuegoLbl.anchor = GridBagConstraints.EAST;
		gbc_estadoVideojuegoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_estadoVideojuegoLbl.gridx = 1;
		gbc_estadoVideojuegoLbl.gridy = 11;
		contentPanel.add(estadoVideojuegoLbl, gbc_estadoVideojuegoLbl);
		
		estadoVideojuegoComboBox = new JComboBox();
		GridBagConstraints gbc_estadoVideojuegoComboBox = new GridBagConstraints();
		gbc_estadoVideojuegoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_estadoVideojuegoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoVideojuegoComboBox.gridx = 2;
		gbc_estadoVideojuegoComboBox.gridy = 11;
		contentPanel.add(estadoVideojuegoComboBox, gbc_estadoVideojuegoComboBox);
		
		JLabel precioLbl = new JLabel("Precio:");
		GridBagConstraints gbc_precioLbl = new GridBagConstraints();
		gbc_precioLbl.anchor = GridBagConstraints.EAST;
		gbc_precioLbl.insets = new Insets(0, 0, 0, 5);
		gbc_precioLbl.gridx = 1;
		gbc_precioLbl.gridy = 13;
		contentPanel.add(precioLbl, gbc_precioLbl);
		
		precioSpinner = new JSpinner();
		GridBagConstraints gbc_precioSpinner = new GridBagConstraints();
		gbc_precioSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_precioSpinner.gridx = 2;
		gbc_precioSpinner.gridy = 13;
		contentPanel.add(precioSpinner, gbc_precioSpinner);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton crearButton = new JButton("Crear");
		crearButton.setAction(new CreateAnuncioAction(this,"Crear"));
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

		JLabel addAnuncioLbl = new JLabel("Crear Anuncio");
		addAnuncioLbl.setFont(new Font("Arial", Font.BOLD, 15));
		titlePane.add(addAnuncioLbl);


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
		addImageButton.setAction(new SelectAnuncioImageAction(this,"Añadir imagen", new ImageIcon(CreateAnuncioDialog.class.getResource("/nuvola/16x16/1545_folder_photo_folder_photo.png"))));
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
		anuncio = new Anuncio();
		imageFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png");
		imageFileChooser.setFileFilter(filter);
		imageFiles = new ArrayList<File>();
		descripcionTextArea.setLineWrap(true);
		descripcionTextArea.setWrapStyleWord(true);
		estadoVideojuegoComboBox.setRenderer(new EstadoVideojuegoListCellRenderer());
		FormatUtils.setTextMaxChars(tituloTextField, 150);
		FormatUtils.setTextMaxChars(descripcionTextArea, 1000);
		textFields = new ArrayList<JTextComponent>();
		textFields.add(descripcionTextArea);
		textFields.add(tituloTextField);
		SwingUtils.changeDateChooserColor(fechaLanzamientoDateChooser, Color.WHITE);
	}
	
	private static boolean videojuegoFilter(Videojuego v, String textToFilter) {
        if (textToFilter.isEmpty()) {
            return true;
        }
        return VideojuegoListCellRenderer.getVideojuegoDisplayText(v).toLowerCase()
                                  .contains(textToFilter.toLowerCase());
    }


	public Anuncio getAnuncio() {
		anuncio.setTitulo(tituloTextField.getText().trim());
		anuncio.setDescripcion(descripcionTextArea.getText().trim());
		anuncio.setFechaInicio(fechaLanzamientoDateChooser.getDate());
		anuncio.setPrecio(Double.valueOf((Integer)precioSpinner.getValue()));
		Videojuego videojuego = (Videojuego)videojuegoComboBox.getSelectedItem();
		anuncio.setIdVideojuego(videojuego.getId());
		EstadoVideojuego estado = (EstadoVideojuego) estadoVideojuegoComboBox.getSelectedItem();
		anuncio.setEstadoVideojuego(estado.getId());
		anuncio.setIdEstadoAnuncio(EstadoAnuncio.ACTIVO);
		anuncio.setIdEmpleado(RetroWorldWindow.getInstance().getEmpleadoAutenticado().getId());
		return this.anuncio;
	}
	
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio=anuncio;
	}

	public void setModel(DefaultComboBoxModel<Videojuego> videojuegoModel, DefaultComboBoxModel<EstadoVideojuego>estadoModel) {
		videojuegoComboBox.setModel(videojuegoModel);
		estadoVideojuegoComboBox.setModel(estadoModel);
	}

	public void setRenderer() {
		ComboBoxFilterDecorator<Videojuego> decorate = ComboBoxFilterDecorator.decorate(videojuegoComboBox,
				VideojuegoListCellRenderer::getVideojuegoDisplayText, 
				CreateAnuncioDialog::videojuegoFilter);
		videojuegoComboBox.setRenderer(new VideojuegoListCellRenderer(decorate.getFilterTextSupplier()));
	}
	
	public void setVideojuegos(Results<Videojuego> videojuegos) {
		this.videojuegos=videojuegos;
	}
	
	public boolean validarTextFields() {
		for(JTextComponent field:textFields) {
			if(field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
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
		return this.anuncio.getId();
	}
}

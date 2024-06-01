package com.pinguela.retroworld.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.OpenModificacionesAction;
import com.pinguela.retroworld.ui.desktop.controller.UpdateAnuncioAction;
import com.pinguela.retroworld.ui.desktop.utils.FormatUtils;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;

public class AnuncioDetailView extends JPanel {
	private static Logger logger = LogManager.getLogger(AnuncioDetailView.class);
	private final int MAIN_IMAGE_WIDTH = 500;
	private final int CARRUSEL_IMAGE_WIDTH = 200;
	
	
	private Anuncio anuncio = null;
	List<Modificacion> modificaciones = null;
	List<BufferedImage> imagenes = null;
	private PaginatedSearchView view;

	private Empleado empleadoAutenticado=null;
	private JTextField tituloValueTextField;
	private JPanel imageCarruselPane;
	private JPanel ImagenesPane;
	private JPanel mainImagePane;
	private JLabel mainImgLbl;
	private JLabel idValueLbl;
	private JTextArea descripcionTextArea;
	private JDateChooser fechaInicioDateChooser;
	private JButton guardarButton;
	private JButton cancelEditButton;
	private JButton bajaButton;
	private JButton editarButton;
	private JFormattedTextField precioValueFormattedTextField;
	private JButton modificacionesButton;

	public AnuncioDetailView(Anuncio a, PaginatedSearchView view) {
		this.view=view;
		this.anuncio=a;

		setLayout(new BorderLayout(0, 0));

		JPanel dataPane = new JPanel();
		add(dataPane, BorderLayout.NORTH);
		GridBagLayout gbl_dataPane = new GridBagLayout();
		gbl_dataPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_dataPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dataPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		dataPane.setLayout(gbl_dataPane);

		JLabel idLbl = new JLabel("Id:");
		idLbl.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_idLbl = new GridBagConstraints();
		gbc_idLbl.anchor = GridBagConstraints.EAST;
		gbc_idLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idLbl.gridx = 0;
		gbc_idLbl.gridy = 0;
		dataPane.add(idLbl, gbc_idLbl);

		idValueLbl = new JLabel("");
		GridBagConstraints gbc_idValueLbl = new GridBagConstraints();
		gbc_idValueLbl.anchor = GridBagConstraints.WEST;
		gbc_idValueLbl.gridwidth = 4;
		gbc_idValueLbl.insets = new Insets(0, 0, 5, 0);
		gbc_idValueLbl.gridx = 1;
		gbc_idValueLbl.gridy = 0;
		dataPane.add(idValueLbl, gbc_idValueLbl);

		JLabel tituloLbl = new JLabel("Título:");
		tituloLbl.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_tituloLbl = new GridBagConstraints();
		gbc_tituloLbl.anchor = GridBagConstraints.EAST;
		gbc_tituloLbl.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLbl.gridx = 0;
		gbc_tituloLbl.gridy = 1;
		dataPane.add(tituloLbl, gbc_tituloLbl);

		tituloValueTextField = new JTextField();
		GridBagConstraints gbc_tituloValueTextField = new GridBagConstraints();
		gbc_tituloValueTextField.gridwidth = 4;
		gbc_tituloValueTextField.insets = new Insets(0, 0, 5, 0);
		gbc_tituloValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloValueTextField.gridx = 1;
		gbc_tituloValueTextField.gridy = 1;
		dataPane.add(tituloValueTextField, gbc_tituloValueTextField);
		tituloValueTextField.setColumns(10);

		JLabel precioLbl = new JLabel("Precio: ");
		precioLbl.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_precioLbl = new GridBagConstraints();
		gbc_precioLbl.anchor = GridBagConstraints.EAST;
		gbc_precioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_precioLbl.gridx = 0;
		gbc_precioLbl.gridy = 2;
		dataPane.add(precioLbl, gbc_precioLbl);
		
		precioValueFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_precioValueFormattedTextField = new GridBagConstraints();
		gbc_precioValueFormattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_precioValueFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioValueFormattedTextField.gridx = 1;
		gbc_precioValueFormattedTextField.gridy = 2;
		dataPane.add(precioValueFormattedTextField, gbc_precioValueFormattedTextField);

		JLabel descripcionLbl = new JLabel("Descripción: ");
		descripcionLbl.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_descripcionLbl = new GridBagConstraints();
		gbc_descripcionLbl.anchor = GridBagConstraints.EAST;
		gbc_descripcionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionLbl.gridx = 0;
		gbc_descripcionLbl.gridy = 3;
		dataPane.add(descripcionLbl, gbc_descripcionLbl);

		descripcionTextArea = new JTextArea();
		GridBagConstraints gbc_descripcionTextArea = new GridBagConstraints();
		gbc_descripcionTextArea.gridheight = 3;
		gbc_descripcionTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descripcionTextArea.gridx = 1;
		gbc_descripcionTextArea.gridy = 3;
		dataPane.add(descripcionTextArea, gbc_descripcionTextArea);

		JLabel fechaInicioLbl = new JLabel("Publicado: ");
		fechaInicioLbl.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_fechaInicioLbl = new GridBagConstraints();
		gbc_fechaInicioLbl.anchor = GridBagConstraints.EAST;
		gbc_fechaInicioLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioLbl.gridx = 0;
		gbc_fechaInicioLbl.gridy = 6;
		dataPane.add(fechaInicioLbl, gbc_fechaInicioLbl);

		fechaInicioDateChooser = new JDateChooser();
		fechaInicioDateChooser.setDateFormatString("dd-MM-yyyy");
		GridBagConstraints gbc_fechaInicioDateChooser = new GridBagConstraints();
		gbc_fechaInicioDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaInicioDateChooser.gridx = 1;
		gbc_fechaInicioDateChooser.gridy = 6;
		dataPane.add(fechaInicioDateChooser, gbc_fechaInicioDateChooser);
		
		modificacionesButton = new JButton();
		modificacionesButton.setAction(new OpenModificacionesAction(this, "Modificaciones",
				new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1872_view_text_list_view_text_list.png"))));
		GridBagConstraints gbc_modificacionesButton = new GridBagConstraints();
		gbc_modificacionesButton.insets = new Insets(0, 0, 0, 5);
		gbc_modificacionesButton.gridx = 3;
		gbc_modificacionesButton.gridy = 7;
		dataPane.add(modificacionesButton, gbc_modificacionesButton);

		JPanel buttonPane = new JPanel();
		add(buttonPane, BorderLayout.SOUTH);

		editarButton = new JButton("Editar");
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditInterface(true);
			}
		});

		guardarButton = new JButton();
		guardarButton.setAction(new UpdateAnuncioAction(this,view, "Guardar"));
		buttonPane.add(guardarButton);
		buttonPane.add(editarButton);

		bajaButton = new JButton("Dar baja");
		bajaButton.setForeground(new Color(0, 0, 0));
		buttonPane.add(bajaButton);

		cancelEditButton = new JButton("Cancelar");
		cancelEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditInterface(false);
				updateView();
			}
		});
		buttonPane.add(cancelEditButton);

		ImagenesPane = new JPanel();
		add(ImagenesPane, BorderLayout.CENTER);
		ImagenesPane.setLayout(new BorderLayout(0, 0));

		JScrollPane imageCarruselScrollPane = new JScrollPane();
		imageCarruselScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		ImagenesPane.add(imageCarruselScrollPane, BorderLayout.SOUTH);

		imageCarruselPane = new JPanel();
		imageCarruselScrollPane.setViewportView(imageCarruselPane);

		mainImagePane = new JPanel();
		ImagenesPane.add(mainImagePane, BorderLayout.CENTER);

		postInitialize();
	}	

	public void postInitialize() {
		try {
			updateView();
			showEditInterface(false);
			empleadoAutenticado = RetroWorldWindow.getInstance().getEmpleadoAutenticado();
			SwingUtils.setEditableDateChooser(fechaInicioDateChooser, false);
			FormatUtils.setDecimalFormat(precioValueFormattedTextField);
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void updateView() {
		tituloValueTextField.setText(anuncio.getTitulo());
		idValueLbl.setText(anuncio.getId().toString());
		descripcionTextArea.setText(anuncio.getDescripcion());
		fechaInicioDateChooser.setDate(anuncio.getFechaInicio());
		precioValueFormattedTextField.setValue(anuncio.getPrecio());
	}
	
	public void updateAnuncio() {
		List<JComponent>fields = new ArrayList<JComponent>();
		fields.add(tituloValueTextField);
		fields.add(descripcionTextArea);
		fields.add(precioValueFormattedTextField);
		modificaciones = addModificacion(fields, anuncio);
		
		anuncio.setPrecio((Double)precioValueFormattedTextField.getValue());
		anuncio.setTitulo(tituloValueTextField.getText());
		anuncio.setDescripcion(descripcionTextArea.getText());
	}
	
	public void printImages() {
		if(!imagenes.isEmpty()) {
			try {
			BufferedImage mainImage = SwingUtils.resizeImage(imagenes.get(0), MAIN_IMAGE_WIDTH);
			JLabel mainImageLbl = new JLabel(new ImageIcon(mainImage));
			mainImagePane.add(mainImageLbl);
			for(BufferedImage imagen:imagenes) {	
					BufferedImage resizedImage = SwingUtils.resizeImage(imagen, CARRUSEL_IMAGE_WIDTH);
					JLabel imageLbl = new JLabel(new ImageIcon(resizedImage));
					addImageOnClickAction(imageLbl, imagen);
					imageCarruselPane.add(imageLbl);
				}
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		}	
	}



	private void addImageOnClickAction(JLabel etiquetaImagen, BufferedImage imagen) {
		etiquetaImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedImage resizedImage=SwingUtils.resizeImage(imagen, MAIN_IMAGE_WIDTH);
					mainImgLbl = new JLabel(new ImageIcon(resizedImage));
					mainImagePane.removeAll();
					mainImagePane.add(mainImgLbl);
					mainImagePane.revalidate();
					mainImagePane.repaint();
				} catch(Exception ex) {
					logger.error(ex.getMessage(), e);
				}
			}
		});
	}

	public void showEditInterface(boolean editable) {
		SwingUtils.setEditableTextField(tituloValueTextField, editable);
		SwingUtils.setEditableTextField(precioValueFormattedTextField, editable);
		SwingUtils.setEditableTextArea(descripcionTextArea, editable);
		cancelEditButton.setVisible(editable);
		guardarButton.setVisible(editable);
		if(editable==false) {
			bajaButton.setVisible(true);
			editarButton.setVisible(true);
		} else {
			editarButton.setVisible(false);
			bajaButton.setVisible(false);
		}

	}
	
	private List<Modificacion> addModificacion(List<JComponent> fields, Anuncio a) {
		List<Modificacion> modificaciones = new ArrayList<Modificacion>();
		for(JComponent field:fields) {
			Modificacion m = new Modificacion();
			if(field instanceof JTextComponent) {
				if(field==tituloValueTextField && !tituloValueTextField.getText().equalsIgnoreCase(anuncio.getTitulo())) {
					if(tituloValueTextField.getText()!=a.getTitulo()) {
						m.setIdTipoModificacion(m.MODIFICACION_TITULO);
					}
				} else if(field==precioValueFormattedTextField && !precioValueFormattedTextField.getValue().equals(anuncio.getPrecio())) {
					m.setIdTipoModificacion(m.MODIFICACION_PRECIO);
				} else if(field==descripcionTextArea && !descripcionTextArea.getText().equalsIgnoreCase(anuncio.getDescripcion())) {
					m.setIdTipoModificacion(m.MODIFICACION_DESCRIPCION);
				}
				if(m.getIdTipoModificacion()!=null) {
					m.setIdAnuncio(a.getId());
					m.setIdEmpleado(empleadoAutenticado.getId());
					m.setTituloAnuncio(anuncio.getTitulo());
					m.setFecha(new Date());
					modificaciones.add(m);
				}
			}
		}
		if(modificaciones.isEmpty()) {
			modificaciones=null;
		}
		return modificaciones;
	}
	
	public Anuncio getAnuncio() {
		return this.anuncio;
	}
	
	public List<Modificacion> getModificaciones(){
		return this.modificaciones;
	}
	
	public void setImages(List<BufferedImage>images) {
		this.imagenes=images;
	}
}

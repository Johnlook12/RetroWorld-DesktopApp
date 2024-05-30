package com.pinguela.retroworld.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.ImageService;
import com.pinguela.retroworld.service.impl.ImageServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.AddMainImageOnClickAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenAsignarGeneroAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenAsignarIdiomaAction;
import com.pinguela.retroworld.ui.desktop.controller.OpenAsignarPlataformaAction;
import com.pinguela.retroworld.ui.desktop.controller.UpdateVideojuegoAction;
import com.pinguela.retroworld.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;

public class VideojuegoDetailView extends JPanel {

	private static Logger logger = LogManager.getLogger(VideojuegoDetailView.class);
	public final int MAIN_IMAGE_WIDTH = 500;
	public final int CARRUSEL_IMAGE_WIDTH = 200;
	public static final String VIEOJUEGO_DETAIL_PROPERTY = "updateDetailView";

	private JLabel idValueLbl;
	private JTextArea descripcionTextArea;
	private JList idiomasList;
	private JList plataformasList;
	private JList generosList;
	private JPanel mainImagePane;
	private JPanel imageCarruselPane;

	private Videojuego videojuego;
	private List<Idioma> idiomas;
	private List<Genero> generos;
	private List<Plataforma> plataformas;

	private ImageService imageService;
	private JButton guardarButton;
	private JButton cancelarButton;
	private JButton editarBtn;
	private JTextField nameTextField;
	private JDateChooser fechaLanzamientoDateChooser;




	public VideojuegoDetailView(Videojuego v) {
		initServices();
		this.videojuego=v;
		setLayout(new BorderLayout(0, 0));

		JPanel dataPane = new JPanel();
		add(dataPane, BorderLayout.EAST);
		dataPane.setLayout(new BorderLayout(0, 0));

		JPanel titlePane = new JPanel();
		dataPane.add(titlePane, BorderLayout.NORTH);
		
		nameTextField = new JTextField();
		titlePane.add(nameTextField);
		nameTextField.setFont(new Font("Arial", Font.BOLD, 15));

		JPanel fieldsPane = new JPanel();
		dataPane.add(fieldsPane, BorderLayout.CENTER);
		GridBagLayout gbl_fieldsPane = new GridBagLayout();
		gbl_fieldsPane.columnWidths = new int[]{85, 66, 43, 46, 0, 0};
		gbl_fieldsPane.rowHeights = new int[]{17, 29, 0, 0, 0, 27, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_fieldsPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_fieldsPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		fieldsPane.setLayout(gbl_fieldsPane);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 5;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 0;
		fieldsPane.add(separator, gbc_separator);

		JLabel idLbl = new JLabel("ID:");
		GridBagConstraints gbc_idLbl = new GridBagConstraints();
		gbc_idLbl.anchor = GridBagConstraints.EAST;
		gbc_idLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idLbl.gridx = 0;
		gbc_idLbl.gridy = 1;
		fieldsPane.add(idLbl, gbc_idLbl);

		idValueLbl = new JLabel("New label");
		GridBagConstraints gbc_idValueLbl = new GridBagConstraints();
		gbc_idValueLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLbl.gridx = 1;
		gbc_idValueLbl.gridy = 1;
		fieldsPane.add(idValueLbl, gbc_idValueLbl);
		
		JLabel fechaLanzamientoLbl = new JLabel("Fecha de lanzamiento: ");
		GridBagConstraints gbc_fechaLanzamientoLbl = new GridBagConstraints();
		gbc_fechaLanzamientoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoLbl.gridx = 0;
		gbc_fechaLanzamientoLbl.gridy = 3;
		fieldsPane.add(fechaLanzamientoLbl, gbc_fechaLanzamientoLbl);
		
		fechaLanzamientoDateChooser = new JDateChooser();
		fechaLanzamientoDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaLanzamientoDateChooser = new GridBagConstraints();
		gbc_fechaLanzamientoDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLanzamientoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaLanzamientoDateChooser.gridx = 1;
		gbc_fechaLanzamientoDateChooser.gridy = 3;
		fieldsPane.add(fechaLanzamientoDateChooser, gbc_fechaLanzamientoDateChooser);

		JLabel descripcionLbl = new JLabel("Descripción:");
		descripcionLbl.setFont(new Font("Arial", Font.BOLD, 11));
		GridBagConstraints gbc_descripcionLbl = new GridBagConstraints();
		gbc_descripcionLbl.anchor = GridBagConstraints.EAST;
		gbc_descripcionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionLbl.gridx = 0;
		gbc_descripcionLbl.gridy = 5;
		fieldsPane.add(descripcionLbl, gbc_descripcionLbl);

		descripcionTextArea = new JTextArea();
		GridBagConstraints gbc_descripcionTextArea = new GridBagConstraints();
		gbc_descripcionTextArea.gridheight = 4;
		gbc_descripcionTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_descripcionTextArea.gridwidth = 4;
		gbc_descripcionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descripcionTextArea.gridx = 1;
		gbc_descripcionTextArea.gridy = 5;
		fieldsPane.add(descripcionTextArea, gbc_descripcionTextArea);

		JLabel idiomasLbl = new JLabel("Idiomas:");
		GridBagConstraints gbc_idiomasLbl = new GridBagConstraints();
		gbc_idiomasLbl.insets = new Insets(0, 0, 5, 5);
		gbc_idiomasLbl.gridx = 0;
		gbc_idiomasLbl.gridy = 10;
		fieldsPane.add(idiomasLbl, gbc_idiomasLbl);

		idiomasList = new JList();
		GridBagConstraints gbc_idiomasList = new GridBagConstraints();
		gbc_idiomasList.gridheight = 2;
		gbc_idiomasList.gridwidth = 2;
		gbc_idiomasList.insets = new Insets(0, 0, 5, 5);
		gbc_idiomasList.fill = GridBagConstraints.BOTH;
		gbc_idiomasList.gridx = 1;
		gbc_idiomasList.gridy = 10;
		fieldsPane.add(idiomasList, gbc_idiomasList);

		JButton addIdiomaButton = new JButton("");
		addIdiomaButton.setAction(new OpenAsignarIdiomaAction(this, "Asignar idiomas",new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1727_add_add.png"))));
		GridBagConstraints gbc_addIdiomaButton = new GridBagConstraints();
		gbc_addIdiomaButton.insets = new Insets(0, 0, 5, 0);
		gbc_addIdiomaButton.gridx = 4;
		gbc_addIdiomaButton.gridy = 10;
		fieldsPane.add(addIdiomaButton, gbc_addIdiomaButton);

		JLabel plataformasLbl = new JLabel("Plataformas:");
		GridBagConstraints gbc_plataformasLbl = new GridBagConstraints();
		gbc_plataformasLbl.insets = new Insets(0, 0, 5, 5);
		gbc_plataformasLbl.gridx = 0;
		gbc_plataformasLbl.gridy = 13;
		fieldsPane.add(plataformasLbl, gbc_plataformasLbl);

		plataformasList = new JList();
		GridBagConstraints gbc_plataformasList = new GridBagConstraints();
		gbc_plataformasList.gridheight = 2;
		gbc_plataformasList.gridwidth = 2;
		gbc_plataformasList.insets = new Insets(0, 0, 5, 5);
		gbc_plataformasList.fill = GridBagConstraints.BOTH;
		gbc_plataformasList.gridx = 1;
		gbc_plataformasList.gridy = 13;
		fieldsPane.add(plataformasList, gbc_plataformasList);

		JButton addPlataformaButton = new JButton("");
		addPlataformaButton.setAction(new OpenAsignarPlataformaAction(this,"Asignar plataformas",new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1727_add_add.png"))));
		GridBagConstraints gbc_addPlataformaButton = new GridBagConstraints();
		gbc_addPlataformaButton.insets = new Insets(0, 0, 5, 0);
		gbc_addPlataformaButton.gridx = 4;
		gbc_addPlataformaButton.gridy = 13;
		fieldsPane.add(addPlataformaButton, gbc_addPlataformaButton);

		JLabel generosLbl = new JLabel("Generos:");
		GridBagConstraints gbc_generosLbl = new GridBagConstraints();
		gbc_generosLbl.insets = new Insets(0, 0, 5, 5);
		gbc_generosLbl.gridx = 0;
		gbc_generosLbl.gridy = 16;
		fieldsPane.add(generosLbl, gbc_generosLbl);

		generosList = new JList();
		GridBagConstraints gbc_generosList = new GridBagConstraints();
		gbc_generosList.gridheight = 2;
		gbc_generosList.gridwidth = 2;
		gbc_generosList.insets = new Insets(0, 0, 0, 5);
		gbc_generosList.fill = GridBagConstraints.BOTH;
		gbc_generosList.gridx = 1;
		gbc_generosList.gridy = 16;
		fieldsPane.add(generosList, gbc_generosList);

		JButton addGeneroButton = new JButton("");
		addGeneroButton.setAction(new OpenAsignarGeneroAction(this, "Asignar géneros", new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/16x16/1727_add_add.png"))));
		GridBagConstraints gbc_addGeneroButton = new GridBagConstraints();
		gbc_addGeneroButton.insets = new Insets(0, 0, 5, 0);
		gbc_addGeneroButton.gridx = 4;
		gbc_addGeneroButton.gridy = 16;
		fieldsPane.add(addGeneroButton, gbc_addGeneroButton);

		JPanel imagesPane = new JPanel();
		add(imagesPane, BorderLayout.CENTER);
		imagesPane.setLayout(new BorderLayout(0, 0));

		mainImagePane = new JPanel();
		imagesPane.add(mainImagePane, BorderLayout.CENTER);

		JScrollPane imageCarruselScrollPane = new JScrollPane();
		imagesPane.add(imageCarruselScrollPane, BorderLayout.SOUTH);

		imageCarruselPane = new JPanel();
		imageCarruselScrollPane.setViewportView(imageCarruselPane);

		JPanel buttonsPane = new JPanel();
		add(buttonsPane, BorderLayout.SOUTH);

		guardarButton = new JButton();
		guardarButton.setAction(new UpdateVideojuegoAction(this, "Guardar"));
		buttonsPane.add(guardarButton);
		

		editarBtn = new JButton("Editar");
		editarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditInterface(true);
			}
		});
		buttonsPane.add(editarBtn);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditInterface(false);
				updateView();
			}
		});
		buttonsPane.add(cancelarButton);

		postInitialize();

	}

	private void postInitialize() {
		updateView();
		showEditInterface(false);
		idiomasList.setCellRenderer(new IdiomaListCellRenderer());
		generosList.setCellRenderer(new GeneroListCellRenderer());
		plataformasList.setCellRenderer(new PlataformaListCellRenderer());
		printImages(videojuego.getId());
		idiomasList.setEnabled(false);
		generosList.setEnabled(false);
		plataformasList.setEnabled(false);
		SwingUtils.changeDateChooserColor(fechaLanzamientoDateChooser, Color.WHITE);
	}
	
	private void showEditInterface(Boolean editable) {
		SwingUtils.setEditableTextArea(descripcionTextArea, editable);
		guardarButton.setVisible(editable);
		cancelarButton.setVisible(editable);
		SwingUtils.setEditableTextField(nameTextField, editable);
		SwingUtils.setEditableDateChooser(fechaLanzamientoDateChooser, editable);

		if(editable==false) {
			editarBtn.setVisible(true);
		}else {
			editarBtn.setVisible(false);
		}
	}
	
	public void updateView() {
		nameTextField.setText(videojuego.getNombre());
		idValueLbl.setText("#"+String.valueOf(videojuego.getId()));
		descripcionTextArea.setText(videojuego.getDescripcion());
		fechaLanzamientoDateChooser.setDate(videojuego.getFechaLanzamiento());
	}
	
	public void updateVideojuego() {
		videojuego.setNombre(nameTextField.getText().trim());
		videojuego.setDescripcion(descripcionTextArea.getText().trim());
		videojuego.setFechaLanzamiento(fechaLanzamientoDateChooser.getDate());
	}

	public void setIdiomas(List<Idioma>idiomas) {
		this.idiomas=idiomas;
		DefaultListModel<Idioma> idiomaModel = new DefaultListModel<Idioma>();
		for(Idioma i:idiomas) {
			idiomaModel.addElement(i);
		}
		idiomasList.setModel(idiomaModel);
	}

	public void setGeneros(List<Genero>generos) {
		this.generos=generos;
		DefaultListModel<Genero> generoModel = new DefaultListModel<Genero>();
		for(Genero g:generos) {
			generoModel.addElement(g);
		}
		generosList.setModel(generoModel);
	}

	public void setPlataformas(List<Plataforma>plataformas) {
		this.plataformas=plataformas;
		DefaultListModel<Plataforma>plataformaModel = new DefaultListModel<Plataforma>();
		for(Plataforma p:plataformas) {
			plataformaModel.addElement(p);
		}
		plataformasList.setModel(plataformaModel);
	}
	

	private void printImages(Long gameId) {

		List<BufferedImage> imagenes = null;
		imagenes = imageService.getVideojuegoImages(gameId);

		if(!imagenes.isEmpty()) {
			try {
				BufferedImage mainImage = SwingUtils.resizeImage(imagenes.get(0), MAIN_IMAGE_WIDTH);
				JLabel mainImageLbl = new JLabel(new ImageIcon(mainImage));
				mainImagePane.add(mainImageLbl);
				for(BufferedImage imagen:imagenes) {	
					BufferedImage resizedImage = SwingUtils.resizeImage(imagen, CARRUSEL_IMAGE_WIDTH);
					JLabel imageLbl = new JLabel(new ImageIcon(resizedImage));
					imageLbl.addMouseListener(new AddMainImageOnClickAction(this, imagen));
					imageCarruselPane.add(imageLbl);
				}
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		}	
	}

	public void addMainImage(JLabel mainImageLbl) {
		mainImagePane.removeAll();
		mainImagePane.add(mainImageLbl);
		mainImagePane.revalidate();
		mainImagePane.repaint();
	}

	private void initServices() {
		imageService = new ImageServiceImpl();
	}
	
	public Videojuego getVideojuego() {
		return this.videojuego;
	}

}

package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.AnuncioSearchView;

public class OpenAnuncioSearchAction 
	extends AbstractAction {
	
	private static Logger logger = LogManager.getLogger(OpenAnuncioSearchAction.class);

	private IdiomaService idiomaService;
	private GeneroService generoService;
	private PlataformaService plataformaService;
	
	public OpenAnuncioSearchAction() {
		initServices();
	}
	
	public OpenAnuncioSearchAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenAnuncioSearchAction(String name, Icon icon) {
		super(name, icon);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AnuncioSearchView view = new AnuncioSearchView();
			List<Idioma> idiomas = idiomaService.findByAll();
			List<Genero> generos = generoService.findByAll();
			List<Plataforma>plataformas = plataformaService.findByAll();
			
			DefaultComboBoxModel<Genero> generoComboModel = new DefaultComboBoxModel<Genero>(generos.toArray(new Genero[generos.size()]));
			DefaultComboBoxModel<Plataforma> plataformaComboModel = new DefaultComboBoxModel<Plataforma>(plataformas.toArray(new Plataforma[plataformas.size()]));
			DefaultComboBoxModel<Idioma> idiomaComboModel = new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));
			SwingUtils.setNullFirstElementModel(generoComboModel);
			SwingUtils.setNullFirstElementModel(plataformaComboModel);
			SwingUtils.setNullFirstElementModel(idiomaComboModel);
			
			view.setModel(generoComboModel, idiomaComboModel, plataformaComboModel);
			view.setStart(new AnuncioPagedSearchAction(PagedSearchAction.START, view,"",
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-left-22.png"))));
			view.setNext(new AnuncioPagedSearchAction(PagedSearchAction.NEXT, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-22.png"))));
			view.setPrevious(new AnuncioPagedSearchAction(PagedSearchAction.PREVIOUS, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-pointing-left-22.png"))));
			view.setEnd(new AnuncioPagedSearchAction(PagedSearchAction.END, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-right-22.png"))));
			
			RetroWorldWindow.getInstance().addClosableView(
					"Buscar anuncio", 
					new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1467_xmag_xmag.png")),
					view);
			
		} catch (DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		idiomaService = new IdiomaServiceImpl();
		generoService = new GeneroServiceImpl();
		plataformaService = new PlataformaServiceImpl();
	}
}

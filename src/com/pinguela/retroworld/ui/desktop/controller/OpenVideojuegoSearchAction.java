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
import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.DesarrolladoraService;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.service.impl.DesarrolladoraServiceImpl;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoSearchView;

public class OpenVideojuegoSearchAction extends AbstractAction{
	
	private static Logger logger = LogManager.getLogger(OpenVideojuegoSearchAction.class);
	
	private IdiomaService idiomaService;
	private PlataformaService plataformaService;
	private GeneroService generoService;
	private DesarrolladoraService desarrolladoraService;
	
	public OpenVideojuegoSearchAction() {
		initServices();
	}
	
	public OpenVideojuegoSearchAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenVideojuegoSearchAction(String name, Icon icon) {
		super(name, icon);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		VideojuegoSearchView view = new VideojuegoSearchView();
		try {
			List<Idioma> idiomas = idiomaService.findByAll();
			List<Genero> generos = generoService.findByAll();
			List<Plataforma> plataformas = plataformaService.findByAll();
			List<Desarrolladora> desarrolladoras = desarrolladoraService.findByAll();

			DefaultComboBoxModel<Idioma> idiomaModel = new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));
			DefaultComboBoxModel<Genero> generoModel = new DefaultComboBoxModel<Genero>(generos.toArray(new Genero[generos.size()]));
			DefaultComboBoxModel<Plataforma> plataformaModel = new DefaultComboBoxModel<Plataforma>(plataformas.toArray(new Plataforma[plataformas.size()]));
			DefaultComboBoxModel<Desarrolladora> desarrolladoraModel = new DefaultComboBoxModel<Desarrolladora>(desarrolladoras.toArray(new Desarrolladora[desarrolladoras.size()]));
			SwingUtils.setNullFirstElementModel(desarrolladoraModel);
			SwingUtils.setNullFirstElementModel(generoModel);
			SwingUtils.setNullFirstElementModel(plataformaModel);
			SwingUtils.setNullFirstElementModel(idiomaModel);
			view.setModel(idiomaModel, plataformaModel, generoModel,desarrolladoraModel);
			view.setStart(new VideojuegoPagedSearchAction(PagedSearchAction.START, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-left-22.png"))));
			view.setNext(new VideojuegoPagedSearchAction(PagedSearchAction.NEXT, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-22.png"))));
			view.setPrevious(new VideojuegoPagedSearchAction(PagedSearchAction.PREVIOUS, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-pointing-left-22.png"))));
			view.setEnd(new VideojuegoPagedSearchAction(PagedSearchAction.END, view, "", 
					new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-arrow-right-22.png"))));
			
			RetroWorldWindow.getInstance().addClosableView("Buscar videojuego", 
					new ImageIcon(RetroWorldWindow.class.getResource("/nuvola/32x32/1467_xmag_xmag.png")),
					view);			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		idiomaService = new IdiomaServiceImpl();
		plataformaService = new PlataformaServiceImpl();
		generoService = new GeneroServiceImpl();
		desarrolladoraService = new DesarrolladoraServiceImpl();
	}

}

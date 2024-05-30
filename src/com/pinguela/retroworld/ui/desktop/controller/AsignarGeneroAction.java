package com.pinguela.retroworld.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarGeneroDialog;

public class AsignarGeneroAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(AsignarGeneroAction.class);
	
	private VideojuegoService videojuegoService;
	private AsignarGeneroDialog dialog;
	
	public AsignarGeneroAction(AsignarGeneroDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarGeneroAction(AsignarGeneroDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarGeneroAction(AsignarGeneroDialog dialog, String name, Icon icon) {
		super(name, icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			List<Genero> generos = dialog.getSelectedGeneros();
			List<Integer> idsGeneros = new ArrayList<Integer>();
			for(Genero g:generos) {
				idsGeneros.add(g.getId());
			}
			videojuegoService.asignarGeneros(dialog.getIdVideojuego(), idsGeneros);
			logger.info("generos con id:"+idsGeneros+" asignados al videojuego con id: "+dialog.getIdVideojuego());
			dialog.dispose();
			JOptionPane.showMessageDialog(dialog, "Generos asignados correctamente");
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}
}

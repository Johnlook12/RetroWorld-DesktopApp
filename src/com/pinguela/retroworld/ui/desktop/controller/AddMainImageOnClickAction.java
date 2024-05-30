package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class AddMainImageOnClickAction implements MouseListener{
	
	private static Logger logger = LogManager.getLogger(AddMainImageOnClickAction.class);
	private VideojuegoDetailView view;
	private BufferedImage imagen;
	
	public AddMainImageOnClickAction(VideojuegoDetailView view, BufferedImage imagen) {
		this.view=view;
		this.imagen=imagen;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			BufferedImage resizedImage=SwingUtils.resizeImage(imagen, view.MAIN_IMAGE_WIDTH);
			JLabel mainImgLbl = new JLabel(new ImageIcon(resizedImage));
			view.addMainImage(mainImgLbl);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), e);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

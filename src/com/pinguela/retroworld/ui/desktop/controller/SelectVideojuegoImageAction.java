package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.pinguela.retroworld.ui.desktop.dialog.CreateVideojuegoDialog;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;

public class SelectVideojuegoImageAction extends BaseAction{
	
	CreateVideojuegoDialog dialog;
	
	public SelectVideojuegoImageAction(CreateVideojuegoDialog dialog) {
		this.dialog=dialog;
	}
	
	public SelectVideojuegoImageAction(CreateVideojuegoDialog dialog, String name) {
		super(name);
		this.dialog = dialog;
	}
	
	public SelectVideojuegoImageAction(CreateVideojuegoDialog dialog, String name, Icon icon) {
		super(name,icon);
		this.dialog = dialog;
	}
	
	@Override
	public void doAction() {
		JFileChooser imageFileChooser = dialog.getImageFileChooser();
		
		int returnVal = imageFileChooser.showOpenDialog(dialog);
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			try {
				File imageFile = imageFileChooser.getSelectedFile();
				dialog.addSelectedImage(imageFile);
				BufferedImage image = ImageIO.read(imageFile);
				BufferedImage resizedImage= SwingUtils.resizeImage(image, 150);
				JLabel imagenLbl = new JLabel(new ImageIcon(resizedImage));
				dialog.setPreviewImageLbl(imagenLbl);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

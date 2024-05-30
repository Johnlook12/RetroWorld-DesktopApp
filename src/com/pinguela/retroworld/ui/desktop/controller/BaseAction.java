package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class BaseAction extends AbstractAction
implements MouseListener, KeyListener, ChangeListener, ItemListener, PropertyChangeListener{

	public BaseAction() {

	}

	public BaseAction(String name, Icon icon) {
		super(name,icon);
	}

	public BaseAction(String name) {
		super(name);
	}

	public abstract void doAction();

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		doAction();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		doAction();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		doAction();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		doAction();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}


}

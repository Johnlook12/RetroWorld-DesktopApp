package com.pinguela.retroworld.ui.desktop.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.imgscalr.Scalr;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class SwingUtils {
	public static final void centerOnScreen(JDialog dialog) {	
		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialog.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialog.getHeight()/2);
	}
	
	public static final void centerOnParent(Window child, boolean absolute) {
		child.pack();
	    boolean useChildsOwner = child.getOwner() != null ? ((child.getOwner() instanceof JFrame) || (child.getOwner() instanceof JDialog)) : false;
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    final Dimension parentSize = useChildsOwner ? child.getOwner().getSize() : screenSize ;
	    final Point parentLocationOnScreen = useChildsOwner ? child.getOwner().getLocationOnScreen() : new Point(0,0) ;
	    final Dimension childSize = child.getSize();
	    childSize.width = Math.min(childSize.width, screenSize.width);
	    childSize.height = Math.min(childSize.height, screenSize.height);
	    child.setSize(childSize);        
	    int x;
	    int y;
	    if ((child.getOwner() != null) && child.getOwner().isShowing()) {
	        x = (parentSize.width - childSize.width) / 2;
	        y = (parentSize.height - childSize.height) / 2;
	        x += parentLocationOnScreen.x;
	        y += parentLocationOnScreen.y;
	    } else {
	        x = (screenSize.width - childSize.width) / 2;
	        y = (screenSize.height - childSize.height) / 2;
	    }
	    if (!absolute) {
	        x /= 2;
	        y /= 2;
	    }
	    child.setLocation(x, y);
	}
	
	public static final void setEditableTextField(JTextField textField,boolean editable) {
		if (editable==false) {
			textField.setEditable(false);
			textField.setBorder(new EmptyBorder(0,0,0,0));
			textField.setBackground(null);
		} else {
			textField.setEditable(true);
			textField.setBorder(UIManager.getBorder("TextField.border"));
			textField.setBackground(UIManager.getColor("TextField.background"));
		}
	}
	
	public static final void setEditableTextArea(JTextArea textArea, boolean editable) {
		if(editable==false) {
			textArea.setEditable(false);
	        textArea.setOpaque(false); 
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
	        textArea.setFont(UIManager.getFont("Label.font")); 
	        textArea.setForeground(UIManager.getColor("Label.foreground"));
		} else {
			textArea.setEditable(true); 
			textArea.setOpaque(true); 
			textArea.setBorder(UIManager.getBorder("TextArea.border")); 
			textArea.setFont(UIManager.getFont("TextArea.font")); 
			textArea.setForeground(UIManager.getColor("TextArea.foreground"));
		}
	}
	
	public static final void setEditableDateChooser(JDateChooser dateChooser, boolean editable) {
		if(editable==false) {
			JTextFieldDateEditor editor = (JTextFieldDateEditor)dateChooser.getDateEditor();
			editor.setEditable(false);
			dateChooser.setEnabled(false);
			dateChooser.getCalendarButton().setVisible(false);
		} else {
			dateChooser.setEnabled(true);
			dateChooser.getCalendarButton().setVisible(true);
		}
	}
	
	public static final void setEditableMultipleTextField(List<JTextField>components, boolean editable) {
		for(JTextField textField:components) {
			setEditableTextField(textField, editable);
		}
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) throws Exception{
		return Scalr.resize(originalImage, targetWidth);
	}
	
	public static void changeDateChooserColor(JDateChooser date, Color color) {
		IDateEditor dateEditor = date.getDateEditor();
		if (dateEditor instanceof JTextFieldDateEditor) {
            JTextFieldDateEditor txtFld = (JTextFieldDateEditor)dateEditor;
            txtFld.setForeground(color);
            txtFld.addPropertyChangeListener("foreground", event -> {
                if (Color.BLACK.equals(event.getNewValue())) {
                    txtFld.setForeground(color);
                }
            });
        }
	}
	
}

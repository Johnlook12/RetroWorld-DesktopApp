package com.pinguela.retroworld.ui.desktop.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

public class FormatUtils {
	
	public FormatUtils() {
	}
	
	public static final void setDecimalFormat(JFormattedTextField textField) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.00);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        
        textField.setFormatterFactory(new DefaultFormatterFactory(formatter));
	}

	public static final void setOnlyDigitsDocument(JTextComponent textField) {
		textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder(getText(0, getLength()));
                sb.insert(offs, str);
                if (sb.toString().isEmpty() || sb.toString().matches("\\d*")) {
                    super.insertString(offs, str, a);
                }
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder(getText(0, getLength()));
                sb.replace(offset, offset + length, text);
                if (sb.toString().isEmpty() || sb.toString().matches("\\d*")) {
                    super.replace(offset, length, text, attrs);
                }
            }
        });
	}
	
	public static final void setTextMaxChars(JTextComponent textField, int maxChars) {
		 ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
	            int maxCharacters = maxChars;
	            
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
	                int futureLength = currentText.length() - length + text.length();
	                
	                if (futureLength <= maxCharacters) {
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    // Bloquea la entrada de más caracteres
	                }
	            }
	        });
	}
	
}

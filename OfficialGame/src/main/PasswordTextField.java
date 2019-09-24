package main;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class PasswordTextField extends TextField {

	private String maskedString = "";
	private String unMaskedString = "";
	private char hidden = '*';
	
	public PasswordTextField(GUIContext container, Font font, int x, int y, int width, int height) {
		super(container, font, x, y, width, height);
	}
	
	
	public void render(GUIContext container, Graphics g) {
	    unMaskedString = super.getText();
		maskedString = "";
		for (int i = 0; i < unMaskedString.length(); i++) {
		  maskedString = maskedString + hidden;
		}
		this.setText(maskedString);
		super.render(container, g);
		this.setText(unMaskedString);
		
	}
	
	@Override
	public String getText() {
		return unMaskedString;
	}

}

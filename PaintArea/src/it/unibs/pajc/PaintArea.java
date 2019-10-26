package it.unibs.pajc;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PaintArea extends JPanel {

	/**
	 * Create the panel.
	 */
	public PaintArea() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		//graphics è il canvas su cui puoi disegnare
		super.paintComponent(g);

		int w = getWidth();
		int h = getHeight();

		int dw=w/8;
		int dh=h/8;
		int i=0;

		if(dw>0&&dh>0)
			for(int x=0;x<=w;x+=dw) 
				for(int y=0;y<=h;y+=dh) {
					//g.drawLine(x, 0, x, h);
					//g.drawLine(0, y, w, y);
					if(i%2==0&&!(dw+x>w||dh+y>h))
						g.fillRect(x, y, dw, dh);
					i++;
				}

	}

}

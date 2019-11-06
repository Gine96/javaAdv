package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintArea extends JPanel implements MouseMotionListener{

	int counter=0;
	int posx=20;
	int dx=1;
	Point mousePos;
	Image avatar;
	
	
	/**
	 * Create the panel.
	 */
	public PaintArea() {
		Timer timer = new Timer(100/6, e->stepNext());
		timer.start();
		
		this.addMouseMotionListener(this);
		
		try {
			avatar = ImageIO.read(new File("resources/cpu.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		//graphics è il canvas su cui puoi disegnare
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();
		
		g.drawLine(0, 0, w, h);

		int dw=w/8;
		int dh=h/8;
		w=dw*8;
		h=dh*8;
		

		if(dw>0&&dh>0)
			for(int x=0;x<=w;x+=dw) 
				for(int y=0;y<=h;y+=dh) {
					g.drawLine(x, 0, x, h);
					g.drawLine(0, y, w, y);
//					if(i%2==0&&!(dw+x>w||dh+y>h))
//						g.fillRect(x, y, dw, dh);
					
				}
		g.drawString(""+counter++, 20, 20);
		g.setColor(Color.GREEN);
		g.fillRect(posx, 100, 100, 100);
		g.setColor(Color.YELLOW);
		if(mousePos!=null) {
			g.fillOval(mousePos.x, mousePos.y, 50, 50);
			g.drawImage(avatar, mousePos.x+10, mousePos.y+10, 30, 30, null);
		}
	}

	public void stepNext() {
		posx+=dx;
		
		if(posx>getWidth()-100||posx<0) {
			dx=-dx;
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getPoint();
		repaint();
	}

}

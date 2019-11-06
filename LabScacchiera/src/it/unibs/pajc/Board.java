package it.unibs.pajc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import it.unibs.pajc.Checkers.PieceColors;
import it.unibs.pajc.Checkers.PieceKinds;


public class Board extends JPanel implements MouseMotionListener,MouseListener{

	private int cellSize;
	private Point mousePos;

	int rows=8;
	int cols=8;

	private Checkers model = new Checkers(rows,cols);
	private String pieceId="";
	boolean selected=false;

	/**
	 * Create the panel.
	 */
	public Board() {

		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}


	@Override
	protected void paintComponent(Graphics g) {
		//graphics è il canvas su cui puoi disegnare
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int w = getWidth();
		int h = getHeight();

		cellSize = (w<h)?w/rows:h/cols;

		paintBoard(g2);
		drawAllPieces(g2);
		paintCursor(g2);

		/*
		 * finisci la logica per spostare le pedine
		 * */

	}


	private void drawAllPieces(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(Checkers.Piece p:model.listPieces()) {
			if(selected&&pieceId.contentEquals(p.id)) {
				g.setColor(Color.WHITE);
				g.fillRect(p.position.x*cellSize, p.position.y*cellSize, cellSize, cellSize);
				for(Point possiblePos : p.possiblePositions()) {
					g.setColor(Color.BLUE);
					g.drawRect(possiblePos.x*cellSize, possiblePos.y*cellSize, cellSize, cellSize);
				}
			}
			if(p.color==PieceColors.BLACK)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.YELLOW);
			g.fillOval(p.position.x*cellSize+5, p.position.y*cellSize+5, cellSize-10, cellSize-10);
//			g.setColor(Color.MAGENTA);
//			g.drawString(String.format("%d,%d", p.position.x, p.position.y), p.position.x*cellSize+20, p.position.y*cellSize+20);
		}

	}

	private void paintCursor(Graphics2D g) {
		g.setStroke(new BasicStroke(3.0f));
		g.setColor(Color.RED);
		int r=cellSize/4;
		if(mousePos!=null){
			int i=mousePos.x/cellSize;
			int j=mousePos.y/cellSize;
			//g.drawString(String.format("%d %d", i,j), 10,10);
			if(i<rows&&j<cols)
				g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
			if(selected) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillOval(mousePos.x-r/2, mousePos.y-r/2, r, r);
			}
		}
	}


	private void paintBoard(Graphics2D g) {
		g.fillRect(0, 0, cellSize*rows, cellSize*cols);
		g.setColor(new Color(240, 176, 93));
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if((i+j)%2==0) 
					continue;
				int x=i*cellSize;
				int y=j*cellSize;
				g.fillRect(x, y, cellSize, cellSize);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos=e.getPoint();
		repaint();

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos=e.getPoint();
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX()/cellSize;
		int y = e.getY()/cellSize;
		if(selected) {
			for(Checkers.Piece p:model.listPieces()) 
				if(pieceId.contentEquals(p.id)) {
					for(Point possiblePos : p.possiblePositions()) {
						if(x==possiblePos.x&&y==possiblePos.y) {
							p.position.x=x;
							p.position.y=y;
							p.eatenControl(p.position);
							selected=false;
						}
					}
				}
		}else {
			for(Checkers.Piece p1:model.listPieces()) 
				if(p1.position.x==x&&p1.position.y==y&&!p1.possiblePositions().isEmpty()) {
					selected=true;
					pieceId=p1.id;
					break;
				}
		}
		model.updateList(!selected);
		repaint();
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

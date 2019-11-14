package it.unibs.pajc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibs.pajc.Checkers.Piece;
import it.unibs.pajc.Checkers.Piece.MoveAndEat;
import it.unibs.pajc.Checkers.PieceColors;

public class GameBoard extends JPanel 
	implements MouseMotionListener, MouseListener {

	private int cellSize;
	private Point cursorPosition;
	
	Image pieceBlack;
	Image pieceWhite;
	
	private Checkers model = new Checkers();	
	
	public GameBoard() {
		
		try {
			pieceBlack = ImageIO.read(new File("./resources/piece_black.png"));
			pieceWhite = ImageIO.read(new File("./resources/piece_white.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMouseMotionListener(this);
		addMouseListener(this);
		

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		int w = getWidth();
		int h = getHeight();
		
		cellSize = (w < h) ? w / 8 : h / 8;
		
		paintBoard(g2);
		paintSelected(g2);
		drawAllPieces(g2);
		drawPossibleMoves(g2);
		drawCursor(g2);
		
	}

	private void drawPossibleMoves(Graphics2D g) {
		if(model.getSelected()!=null) {
			for(MoveAndEat move : model.getSelected().validMoves()) {
				fillCell(g, move.position.x, move.position.y, Color.MAGENTA);
			}
		}
	}

	private void paintSelected(Graphics2D g) {
		Piece p = model.getSelected();
		if(p==null)
			return;
		
		fillCell(g, p.position.x, p.position.y, Color.RED);
		
		
	}

	private void drawAllPieces(Graphics2D g) {
		for(Checkers.Piece p: model.listPieces())
			drawPiece(g, p);
	}

	private void drawPiece(Graphics2D g, Checkers.Piece p) {
		
		Image img = p.color == PieceColors.BLACK ? pieceBlack : pieceWhite;
		
		g.drawImage(img, p.position.x*cellSize+5, p.position.y*cellSize+5, cellSize-10, cellSize-10, null);
		
	}

	private void drawCursor(Graphics2D g) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(3f));
		
		if(cursorPosition != null) {
			int i = cursorPosition.x / cellSize;
			int j = cursorPosition.y / cellSize;
			if(i <= 7 && j <= 7) {
			g.drawRect(i*cellSize, j*cellSize, 
					cellSize, cellSize);			
			}
		}
	}

	private void paintBoard(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, cellSize*8, cellSize*8);
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if( (i+j) % 2 != 0) 
					continue;
				
				fillCell(g, i, j, Color.LIGHT_GRAY);
				
			}
		}
	}
	
	private void fillCell(Graphics2D g, int i, int j, Color c) {
		int x = i * cellSize ;
		int y = j * cellSize ;
		
		g.setColor(c);
		g.fillRect(x, y, cellSize, cellSize);
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		cursorPosition = e.getPoint();
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		
		int i = e.getPoint().x / cellSize;
		int j = e.getPoint().y / cellSize;
		Point mouseClickPos = new Point(i,j);
		
		if(model.getSelected()!=null) {
			for(MoveAndEat move : model.getSelected().validMoves()) {
				if(move.position.equals(mouseClickPos)) {
					model.getSelected().position=move.position;
				}
			}
		}
		
		Piece p = model.getPieceAtPos(mouseClickPos);
		model.setSelected(p);
		
		repaint();
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}


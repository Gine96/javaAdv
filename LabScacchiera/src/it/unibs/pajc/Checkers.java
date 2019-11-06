package it.unibs.pajc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Checkers {

	enum PieceColors{ BLACK, WHITE};
	enum PieceKinds{ MEN, KING};

	public class Piece{
		String id;
		Point position;
		PieceColors color;
		PieceKinds kind;

		public Piece(String id, Point position, PieceColors color, PieceKinds kind) {
			this.id=id;
			this.position=position;
			this.color=color;
			this.kind=kind;
		}

		public ArrayList<Point> possiblePositions(){
			ArrayList<Point> positions = new ArrayList<Point>();

			if(this.kind.equals(PieceKinds.MEN)) {
				for(int i=0;i<2;i++) {
					int posx=this.position.x+((i%2)*2-1);
					int posy=this.position.y+(this.color.equals(PieceColors.WHITE)?+1:-1);
					Point possPos = new Point(posx,posy);
					if(posx<cols&&posx>=0&&posy<rows&&posy>=0) 
						positions.add(possPos);

				}
			}else {
				for(int i=0;i<2;i++) {
					for(int j=0;j<2;j++) {
						int posx=this.position.x+(i%2==0?+1:-1);
						int posy=this.position.y+(j%2==0?+1:-1);
						Point possPos = new Point(posx,posy);
						if(posx<cols&&posx>=0&&posy<rows&&posy>=0) 
							positions.add(possPos);
					}
				}
			}

			ArrayList<Point> posToRemove = new ArrayList<Point>();

			boolean clear=false;

			for(Point nextPos : positions) {
				Piece piece = this.getPieceFromPosition(nextPos);
				if(piece!=null) {
					if(piece.color.equals(this.color))
						posToRemove.add(nextPos);
					if(!piece.color.equals(this.color)&&(this.kind.equals(PieceKinds.KING)||piece.kind.equals(PieceKinds.MEN))) {
						posToRemove.add(nextPos);
						int nextx=-2*(this.position.x-nextPos.x)+this.position.x;
						int nexty=-2*(this.position.y-nextPos.y)+this.position.y;
						nextPos=new Point(nextx,nexty);
						if(nextx<cols&&nextx>=0&&nexty<rows&&nexty>=0&&this.getPieceFromPosition(nextPos)==null) {
							clear=true;
							mangiabili.put(nextPos, piece);
						}
					}
				}
			}

			positions.removeAll(posToRemove);
			if(clear)
				positions.clear();

			for(Point pnt : mangiabili.keySet()) 
				positions.add(pnt);

			positions.add(this.position);

			return positions;
		}

		public Piece getPieceFromPosition(Point point) {
			for(Piece piece : pieces.values()) 
				if(point.equals(piece.position)) 
					return piece;
			return null;
		}

		public String toString() {
			return String.format("%s %dx%d", id, position.x, position.y);
		}

		public void eatenControl(Point position2) {
			if(mangiabili.containsKey(position2)) {
				piecesToRemove.add(mangiabili.get(position2));
				mangiabili.clear();
			}
		}
	}

	private HashMap<String, Piece> pieces = new HashMap<String, Piece>();
	private int rows,cols;
	private ArrayList<Piece> piecesToRemove = new ArrayList<Checkers.Piece>();
	HashMap<Point, Piece> mangiabili = new HashMap<Point, Checkers.Piece>();

	public Checkers(int rows, int cols) {
		this.rows=rows;
		this.cols=cols;
		int k=0;
		for(int i=0;i<rows;i++) {
			if(i>2&&i<rows-3)
				continue;

			for(int j=0;j<cols;j++) {
				if((i+j)%2!=0) 
					continue;

				Piece p = new Piece(""+k++, new Point(j, i), i<3?PieceColors.WHITE:PieceColors.BLACK, PieceKinds.MEN);
				pieces.put(p.id, p);
			}
		}
	}

	public void updateList(boolean selected) {
		if(selected) 
			pieces.values().removeAll(piecesToRemove);
		else
			for(Piece piece : pieces.values()) {
				for(int i=0;i<cols;i+=2) {
					if(piece.color.equals(PieceColors.BLACK)) {
						if(piece.position.x==i&&piece.position.y==0)
							piece.kind=PieceKinds.KING;
					}else {
						if(piece.position.equals(new Point(rows-1,i)))
							piece.kind=PieceKinds.KING;
					}
				}
			}

	}

	public Collection<Checkers.Piece> listPieces(){
		return pieces.values();
	}

}

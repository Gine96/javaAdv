package it.unibs.pajc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.xml.validation.Validator;

public class Checkers {
	
	enum PieceColors { BLACK, WHITE };
	enum PieceKinds { MEN, KING };
	private Piece selected;
	private int size=8;
	
	public class Piece {
		String id;
		Point position;
		PieceColors color;
		PieceKinds kind;
		
		public Piece(String id, Point position, PieceColors color, PieceKinds kind) {
			this.id = id;
			this.position = position;
			this.color = color;
			this.kind = kind;
		}
		
		boolean isValidBoardPosition(int x, int y) {
			return x>=0&&y>=0&&x<size&&y<size;
		}
		
		boolean move(Point p, boolean right, boolean up) {
			int x = p.x+(right?+1:-1);
			int y = p.y+(up?-1:+1);
			
			if(isValidBoardPosition(x, y)) {
				p.x=x;
				p.y=y;
				return true;
			}
			
			return false;
		}
		
		class MoveAndEat{
			Point position;
			ArrayList<Piece> eatedPiece;
			
			public MoveAndEat(Point position, ArrayList<Piece> eatedPieces) {
//				this.position = position;
				/*
				 * qua c'è un problema:
				 * se aggiorno position cosi la classe cambia riferimento della posizione che gli passo
				 */
				this.position = (Point)position.clone();
				this.eatedPiece = (eatedPieces!=null)?(ArrayList<Checkers.Piece>)eatedPieces:new ArrayList<Checkers.Piece>();
				
			}
		}
		
		/*
		 * visualizza le mosse possibili
		 */

		public List<MoveAndEat> validMoves(){
			return validMoves(new MoveAndEat(this.position, null), new ArrayList<Checkers.Piece.MoveAndEat>());
		}
		
		private List<MoveAndEat> validMoves(MoveAndEat start, List<MoveAndEat> moves){
			validMoveStep(start, moves, true, color == PieceColors.BLACK);
			validMoveStep(start, moves, false, color == PieceColors.BLACK);
			
			return moves;
		}
		
		private void validMoveStep(MoveAndEat start, List<MoveAndEat> moves, boolean right, boolean up) {
			
			MoveAndEat move = new MoveAndEat(start.position, start.eatedPiece);
			
			if(move(move.position, right,up)) {
				Piece eat = getPieceAtPos(move.position);
				if(eat==null) {
					moves.add(move);
				}else if(eat.color!=this.color && move(move.position,right,up) && getPieceAtPos(move.position.x,move.position.y)==null){
					move.eatedPiece.add(eat);
					moves.add(move);
					validMoves(move, moves);
				}
			}
			
		}
		
		@Override
		public String toString() {
			return String.format("%s, %dx%d", id, position.x, position.y);
		}
	}

	private HashMap<String, Piece> pieces = new HashMap<>();
	public Checkers() {
		int k = 0;
		
		for(int i=0; i<size; i++) { // scorro per riga la scacchiera
			if(i > 2 && i < size-3)
				continue;
			
			for(int j=0; j<size; j++) { // data una riga per ogni colonna...
			
				if( (i+j) % 2 != 0)
					continue;
				
				Piece p = new Piece(""+k++,	new Point(j, i), (i < 3) ? PieceColors.WHITE : PieceColors.BLACK, PieceKinds.MEN);
				
				pieces.put(p.id, p);
				
			}
		}
	}
	
	public Collection<Checkers.Piece> listPieces() {
		return pieces.values();
	}

	public Piece getSelected() {
		return selected;
	}

	public void setSelected(Piece selected) {
		this.selected = selected;
	}
	
	boolean isValidBoardPosition(int x, int y) {
		return x>=0&&y>=0&&x<size&&y<size;
	}

	public Piece getPieceAtPos(Point mouseClickPos) {
		for(Piece p : pieces.values())
			if(p.position.equals(mouseClickPos))
				return p;
		return null;
	}
	
	public Piece getPieceAtPos(int i, int j) {
		return getPieceAtPos(new Point(i,j));
	}
	
	
	
}

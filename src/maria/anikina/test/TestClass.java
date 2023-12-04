package maria.anikina.test;

import maria.anikina.service.Board;
import maria.anikina.service.Game;
import maria.anikina.service.Game2048;
import maria.anikina.service.SquareBoard;

import java.util.Arrays;
import java.util.List;

public class TestClass {
	public static void main(String[] args) {
		Board board = new SquareBoard(2);
		board.fillBoard(Arrays.asList(2, 4, 4, 3));
		Game game2048 = new Game2048(board);
		System.out.println(game2048.canMove());
	}
}

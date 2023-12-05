package maria.anikina.test;

import maria.anikina.model.Board;
import maria.anikina.service.Direction;
import maria.anikina.service.Game2048;
import maria.anikina.service.SquareBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	private Board board = new SquareBoard(4);
	private Game2048 game2048 = new Game2048(board);

	@Test
	public void checkGameWorking() {
		game2048.init();
		System.out.println("Исходное поле:");
		showField();
		System.out.println("Поле после свайпа влево:");
		game2048.move(Direction.LEFT);
		showField();
		System.out.println("Поле после добавления нового элемента: ");
		game2048.addItem();
		showField();
		System.out.println("Поле после свайпа вправо:");
		game2048.move(Direction.RIGHT);
		showField();
		System.out.println("Поле после добавления нового элемента");
		game2048.addItem();
		showField();
		System.out.println("Поле после свайпа вверх:");
		game2048.move(Direction.UP);
		showField();
		System.out.println("Поле после добавления нового элемента");
		game2048.addItem();
		showField();
		System.out.println("Поле после свайпа вниз:");
		game2048.move(Direction.DOWN);
		showField();

		assertTrue(game2048.canMove());
		assertFalse(game2048.hasWin());
	}

	private void showField() {
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				System.out.print(board.getValue(board.getKey(i, j))+ "  ");
			}
			System.out.println("");
		}
		System.out.println(" ");
	}
}

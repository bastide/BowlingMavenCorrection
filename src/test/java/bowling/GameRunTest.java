package bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameRunTest {

	private SinglePlayerGame game;

	@BeforeEach
	public void setUp() {
		game = new SinglePlayerGame();
	}

	/**
	 * Si on envoie toutes les 20 boules dans la rigole, le score final est 0
	 */
	@Test
	public void testBeginGame() {
		assertEquals(1, game.getFrameNumber(),
                        "On doit commencer au tour   n°1");
		assertEquals(1, game.getNextBallNumber(),
                        "On doit commencer à la boule n°1");
	}

	@Test
	public void testAllOnes() {
		rollMany(20, 1);
		assertEquals(0, game.getFrameNumber(),
                        "On a fini, le n° de tour doit être 0");
		assertTrue(game.hasCompletedFrame(),
                        "Le tour courant doit être terminé");
		assertTrue(game.isFinished(),
                        "Le jeu doit être terminé");
	}

	@Test
	public void testOneSpare()  {
		rollSpare(); 
		assertTrue(game.hasCompletedFrame(),
                        "Le tour courant doit être terminé");
		assertEquals(2, game.getFrameNumber(),
                        "On a fini le premier tour, le n° de tour doit être 2");
		assertEquals(1, game.getNextBallNumber(),
                        "On doit commencer le tour à la boule n°1");
	}

	@Test
	public void testOneStrike()  {
		rollStrike(); // 10 + 7
		assertTrue(game.hasCompletedFrame(),
                        "Le tour courant doit être terminé");
		assertEquals(2, game.getFrameNumber(),
                        "On a fini le premier tour, le n° de tour doit être 2");
		assertEquals(1, game.getNextBallNumber(),
                        "On doit commencer le tour à la boule n°1");
	}


	// Quelques methodes utilitaires pour faciliter l'écriture des tests
	private void rollMany(int n, int pins) {
		for (int i = 0; i < n; i++) {
			game.lancer(pins);
		}
	}

	private void rollSpare() {
		game.lancer(7);
		game.lancer(3);
	}

	private void rollStrike() {
		game.lancer(10);
	}

}

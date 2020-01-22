package bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameRunTest {

	private SinglePlayerGame game;

	@Before
	public void setUp() {
		game = new SinglePlayerGame();
	}

	/**
	 * Si on envoie toutes les 20 boules dans la rigole, le score final est 0
	 */
	@Test
	public void testBeginGame() {
		assertEquals("On doit commencer au tour   n°1", 1, game.getFrameNumber());
		assertEquals("On doit commencer à la boule n°1", 1, game.getNextBallNumber());
	}

	@Test
	public void testAllOnes() {
		rollMany(20, 1);
		assertEquals("On a fini, le n° de tour doit être 0", 0, game.getFrameNumber());
		assertTrue("Le tour courant doit être terminé", game.hasCompletedFrame());
		assertTrue("Le jeu doit être terminé", game.isFinished());
	}

	@Test
	public void testOneSpare()  {
		rollSpare(); 
		assertTrue("Le tour courant doit être terminé", game.hasCompletedFrame());
		assertEquals("On a fini le premier tour, le n° de tour doit être 2", 2, game.getFrameNumber());
		assertEquals("On doit commencer le tour à la boule n°1", 1, game.getNextBallNumber());
	}

	@Test
	public void testOneStrike()  {
		rollStrike(); // 10 + 7
		assertTrue("Le tour courant doit être terminé", game.hasCompletedFrame());
		assertEquals("On a fini le premier tour, le n° de tour doit être 2", 2, game.getFrameNumber());
		assertEquals("On doit commencer le tour à la boule n°1", 1, game.getNextBallNumber());
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

package server;

import java.util.ArrayList;
import java.util.List;

import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;
import model.Turn;

final class TurnFactory {
	private final GameTestMode testMode;
	private final List<Turn> turns;

	private Integer sequence = 0;

	public TurnFactory(GameTestMode testMode) {
		this.testMode = testMode;
		this.turns = null;
	}

	public TurnFactory(List<Turn> turns) {
		this.testMode = null;
		this.turns = turns;
	}

	public TurnFactory() {
		this.testMode = null;
		this.turns = new ArrayList<>();
	}

	private final List<Turn> sequenceATurns = new ArrayList<Turn>() {
		private static final long serialVersionUID = 1L;

		{
			add(new Turn(new FortuneCard(FortuneCardType.SKULLS, 2),
					new DieFace[][] { { DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
							DieFace.PARROT, DieFace.PARROT, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.PARROT, DieFace.PARROT,
					DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY, DieFace.SKULL, DieFace.SKULL, DieFace.SWORD } }));

			add(new Turn(new FortuneCard(FortuneCardType.CAPTAIN), new DieFace[][] { { DieFace.SWORD, DieFace.SWORD,
					DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD } }));
		}
	};

	private final List<Turn> sequenceBTurns = new ArrayList<Turn>() {
		private static final long serialVersionUID = 313869311888714695L;

		{
			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.CAPTAIN),
					new DieFace[][] {
							{ DieFace.COIN, DieFace.PARROT, DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND,
									DieFace.PARROT, DieFace.COIN, DieFace.PARROT },
							{ DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD,
									DieFace.SWORD, DieFace.SWORD } }));
		}
	};

	public Turn createAndIncrementTurn() {
		Turn turn = getCurrentTurn();
		sequence++;
		return turn;
	}

	public void addTurn(Turn turn) {
		turns.add(turn);
	}

	public Turn getCurrentTurn() {
		if (testMode != null) {
			switch (testMode) {
			case SEQUENCE_A:
				return sequenceATurns.get(sequence);
			default:
				return sequenceBTurns.get(sequence);
			}
		}

		if (sequence >= turns.size()) {
			return new Turn();
		} else {
			return turns.get(sequence);
		}
	}
}

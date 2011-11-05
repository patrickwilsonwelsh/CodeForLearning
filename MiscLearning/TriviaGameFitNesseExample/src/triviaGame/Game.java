package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	public static final int MAX_GOLD_COINS = 6;
	public static final int MAX_PLAYERS = 6;

	private Board theBoard;
	private RuleBook ruleBook;
	private ArrayList<Player> players;
	private boolean gameHasStarted;
	private int currentPlayer;
	private Player winningPlayer;

	public Game() {
		theBoard = new Board();
		ruleBook = new RuleBook();
		players = new ArrayList<Player>();
		gameHasStarted = false;
	}

	public Player addPlayer(String aPlayerName) {
		if (gameHasStarted)
			return null;
		if (playerIsPlaying(aPlayerName))
			return null;
		if (getNumberOfPlayers() == Game.MAX_PLAYERS)
			return null;
		Player aPlayer = Player.newPlayerWithPlace(aPlayerName, theBoard
				.getFirstPlace());
		players.add(aPlayer);
		currentPlayer = 0;
		return aPlayer;
	}

	public boolean playerIsPlaying(Player aPlayer) {
		return players.contains(aPlayer);
	}

	public boolean playerIsPlaying(String aPlayerName) {
		return (getPlayerNamed(aPlayerName) != null);
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public boolean isPlayable() {
		return (getNumberOfPlayers() > 1);
	}

	public Board getBoard() {
		return theBoard;
	}

	public void removePlayer(Player aPlayer) {
		if (!gameHasStarted)
			players.remove(aPlayer);
	}

	public boolean gameHasStarted() {
		return gameHasStarted;
	}

	public void takeTurn() throws Throwable {
		takeTurn(1, true);
	}

	public TurnResult takeTurn(int roll) throws Throwable {
		return takeTurn(roll, true);
	}

	public TurnResult takeTurn(int roll, boolean rightAnswer) {
		gameHasStarted = true;

		TurnResult turnResult = new TurnResult(roll, rightAnswer,
				whoseTurnIsIt());
		Player winningPlayer = ruleBook.interpret(turnResult, theBoard);
		if (winningPlayer == null)
			getNextPlayer();
		else
			winTheGame(winningPlayer);

		return turnResult;
	}

	private void getNextPlayer() {
		if (currentPlayer < getNumberOfPlayers() - 1)
			currentPlayer++;
		else
			currentPlayer = 0;
	}

	private void winTheGame(Player player) {
		winningPlayer = player;
	}

	public Player getPlayerNamed(String aName) {
		Iterator playerIt = players.iterator();
		while (playerIt.hasNext()) {
			Player aPlayer = (Player) playerIt.next();
			if (aPlayer.getName().equals(aName)) {
				return aPlayer;
			}
		}
		return null;
	}

	public String getPlayersForPlace(int placeId) {
		return theBoard.getPlayersForPlace(players, placeId);
	}

	public boolean playerHasWonGame(Player aPlayer) {
		return (winningPlayer == aPlayer);
	}

	public Player whoseTurnIsIt() {
		return (Player) players.get(currentPlayer);
	}

	public boolean removeAllPlayers() {
		players.clear();
		return (getNumberOfPlayers() == 0);
	}

	public Object[] getAllPlayers() {
		return players.toArray();
	}
}
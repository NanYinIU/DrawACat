package cat.designPatterns.memento;

public class Memento {

    private GameState gameState;

    Memento(GameState gameState) {
        this.gameState = gameState;
    }

    GameState getGameState() {
        return gameState;
    }

}

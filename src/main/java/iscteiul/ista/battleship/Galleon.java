package iscteiul.ista.battleship;

/**
 * Representa o navio do tipo Galeão no jogo da Batalha Naval.
 * O Galeão é uma embarcação que ocupa 5 posições no tabuleiro, calculadas
 * com base na sua posição base e na sua orientação.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Construtor da classe Galleon.
     * Inicializa o navio com a sua orientação e posição base, preenchendo
     * automaticamente as restantes coordenadas que ocupa no tabuleiro.
     *
     * @param bearing A orientação do navio (ex: NORTH, SOUTH, EAST, WEST).
     * @param pos     A posição base (coordenada de referência) do navio.
     * @throws IllegalArgumentException Se a orientação fornecida for nula ou inválida.
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Obtém o tamanho fixo do Galeão.
     *
     * @return O número de posições ocupadas pelo navio (5).
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Norte.
     *
     * @param pos A posição base do navio.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Sul.
     *
     * @param pos A posição base do navio.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Este.
     *
     * @param pos A posição base do navio.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Oeste.
     *
     * @param pos A posição base do navio.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}

package iscteiul.ista.battleship;

/**
 * Representa o navio do tipo Fragata no jogo da Batalha Naval.
 * A Fragata é uma embarcação que ocupa 4 posições consecutivas no tabuleiro,
 * dispostas em linha reta, quer na vertical ou na horizontal.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Construtor da classe Frigate.
     * Inicializa o navio com a sua orientação e posição base, preenchendo
     * automaticamente as restantes coordenadas que ocupa no tabuleiro.
     * Para a Fragata, a orientação determina se o navio se estende na vertical (NORTH/SOUTH)
     * ou na horizontal (EAST/WEST).
     *
     * @param bearing A orientação do navio (ex: NORTH, SOUTH, EAST, WEST).
     * @param pos     A posição base (coordenada de referência inicial) do navio.
     * @throws IllegalArgumentException Se a orientação fornecida for inválida.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Obtém o tamanho fixo da Fragata.
     *
     * @return O número de posições ocupadas pelo navio (4).
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}

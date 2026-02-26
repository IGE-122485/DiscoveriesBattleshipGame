/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo "Nau" (Carrack) no jogo Batalha Naval.
 * <p>
 * Esta classe estende a funcionalidade da classe base {@link Ship}. A Nau 
 * é uma embarcação de tamanho intermédio, ocupando 3 posições consecutivas 
 * no tabuleiro de jogo. A sua orientação determina como estas posições 
 * são calculadas a partir do ponto de origem.
 * </p>
 *
 * @author O Seu Nome / A Sua Equipa
 * @version 1.0
 */
public class Carrack extends Ship {
    
    /** Tamanho padrão da nau (ocupa 3 células no tabuleiro). */
    private static final Integer SIZE = 3;
    
    /** Nome identificativo deste tipo de embarcação. */
    private static final String NAME = "Nau";

    /**
     * Construtor da classe Carrack.
     * <p>
     * Inicializa a nau a partir de uma posição âncora e calcula as restantes 
     * coordenadas com base na orientação (Norte/Sul para expansão vertical, 
     * Este/Oeste para expansão horizontal).
     * </p>
     *
     * @param bearing A orientação geográfica da embarcação.
     * @param pos     A coordenada inicial (ponto de partida) da embarcação no tabuleiro.
     * @throws IllegalArgumentException Se a orientação (bearing) não for reconhecida, 
     * impossibilitando o cálculo das posições ocupadas.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém o tamanho da nau.
     *
     * @return O número de posições que o navio ocupa, que corresponde a {@value #SIZE}.
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}

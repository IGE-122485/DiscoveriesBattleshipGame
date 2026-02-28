/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo "Barca" (Barge) no jogo Batalha Naval.
 * <p>
 * Esta classe estende a funcionalidade base de {@link Ship}, definindo as 
 * características específicas de uma barca. Uma barca é o navio mais pequeno, 
 * ocupando apenas uma única posição no tabuleiro.
 * COMENTÁRIO para gerar conflito
 * </p>

 */
public class Barge extends Ship {
    
    /** Tamanho padrão da barca (ocupa 1 célula no tabuleiro). */
    private static final Integer SIZE = 1;
    
    /** Nome identificativo deste tipo de navio. */
    private static final String NAME = "Barca";

    /**
     * Construtor da classe Barge.
     * <p>
     * Inicializa a barca definindo a sua orientação e a sua posição inicial. 
     * Dado que o tamanho da barca é 1, a lista de posições ocupadas conterá 
     * unicamente a coordenada especificada no parâmetro {@code pos}.
     * </p>
     *
     * @param bearing A orientação do navio (Norte, Sul, Este, Oeste).
     * @param pos     A posição superior esquerda onde o navio é colocado no tabuleiro.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Obtém o tamanho da barca.
     *
     * @return O número de posições que o navio ocupa, que será sempre {@value #SIZE}.
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}

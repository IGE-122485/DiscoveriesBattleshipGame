/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo "Caravela" (Caravel) no jogo Batalha Naval.
 * <p>
 * Esta classe estende a classe base {@link Ship}. A caravela ocupa 
 * 2 posições consecutivas no tabuleiro. A disposição exata destas posições 
 * é calculada dinamicamente no momento da instanciação, dependendo da orientação escolhida.
 * </p>
 *
 * @author O Seu Nome / A Sua Equipa
 * @version 1.0
 */
public class Caravel extends Ship {
    
    /** Tamanho padrão da caravela (ocupa 2 células no tabuleiro). */
    private static final Integer SIZE = 2;
    
    /** Nome identificativo deste tipo de embarcação. */
    private static final String NAME = "Caravela";

    /**
     * Construtor da classe Caravel.
     * <p>
     * Inicializa a caravela calculando as suas coordenadas no tabuleiro a partir 
     * de um ponto inicial. Se a orientação for Norte ou Sul, a caravela expande-se 
     * verticalmente (adicionando linhas). Se for Este ou Oeste, expande-se 
     * horizontalmente (adicionando colunas).
     * </p>
     *
     * @param bearing A orientação para a qual a caravela está voltada (ex: NORTH, EAST).
     * @param pos     A posição inicial (âncora) para a colocação da caravela.
     * @throws NullPointerException     Se o parâmetro {@code bearing} fornecido for nulo.
     * @throws IllegalArgumentException Se o parâmetro {@code bearing} não for reconhecido 
     * pela lógica de orientação do navio.
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Obtém o tamanho da caravela.
     * * @return O número de posições que o navio ocupa, que será sempre {@value #SIZE}.
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}

package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa um navio do jogo Battleship.
 * Contém informação sobre tamanho, posição, orientação e estado.
 */
public interface IShip {

    /**
     * Devolve a categoria do navio.
     *
     * @return categoria do navio
     */
    String getCategory();

    /**
     * Devolve o tamanho do navio.
     *
     * @return número de posições ocupadas
     */
    Integer getSize();

    /**
     * Devolve as posições ocupadas pelo navio.
     *
     * @return lista de posições
     */
    List<IPosition> getPositions();

    /**
     * Devolve a posição inicial do navio.
     *
     * @return posição inicial
     */
    IPosition getPosition();

    /**
     * Devolve a orientação do navio.
     *
     * @return direção (bearing)
     */
    Compass getBearing();

    /**
     * Indica se o navio ainda não foi afundado.
     *
     * @return true se ainda estiver a flutuar
     */
    boolean stillFloating();

    /**
     * Devolve a linha mais superior ocupada pelo navio.
     *
     * @return índice da linha superior
     */
    int getTopMostPos();

    /**
     * Devolve a linha mais inferior ocupada pelo navio.
     *
     * @return índice da linha inferior
     */
    int getBottomMostPos();

    /**
     * Devolve a coluna mais à esquerda ocupada pelo navio.
     *
     * @return índice da coluna esquerda
     */
    int getLeftMostPos();

    /**
     * Devolve a coluna mais à direita ocupada pelo navio.
     *
     * @return índice da coluna direita
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa a posição indicada.
     *
     * @param pos posição a verificar
     * @return true se ocupar essa posição
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio.
     *
     * @param other navio a comparar
     * @return true se estiverem demasiado próximos
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se este navio está demasiado próximo de uma posição.
     *
     * @param pos posição a verificar
     * @return true se estiver demasiado próximo
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo numa posição do navio.
     *
     * @param pos posição atingida
     */
    void shoot(IPosition pos);
}

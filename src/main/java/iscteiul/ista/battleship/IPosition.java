package iscteiul.ista.battleship;

/**
 * Representa uma posição no tabuleiro do jogo.
 * Controla o seu estado (ocupada, atingida, etc.).
 */
public interface IPosition {

    /**
     * Devolve a linha da posição.
     *
     * @return número da linha
     */
    int getRow();

    /**
     * Devolve a coluna da posição.
     *
     * @return número da coluna
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto.
     *
     * @param other objeto a comparar
     * @return true se representarem a mesma posição
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra.
     *
     * @param other posição a comparar
     * @return true se forem adjacentes
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca a posição como ocupada.
     */
    void occupy();

    /**
     * Marca a posição como alvo de um disparo.
     */
    void shoot();

    /**
     * Indica se a posição está ocupada por um navio.
     *
     * @return true se estiver ocupada
     */
    boolean isOccupied();

    /**
     * Indica se a posição já foi atingida.
     *
     * @return true se tiver sido atingida
     */
    boolean isHit();
}

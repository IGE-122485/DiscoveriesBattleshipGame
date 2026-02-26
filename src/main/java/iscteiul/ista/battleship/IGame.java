package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa o jogo Battleship.
 * Controla os disparos efetuados e estatísticas do jogo.
 */
public interface IGame {

    /**
     * Efetua um disparo na posição indicada.
     *
     * @param pos posição alvo
     * @return navio atingido ou null se não houver navio
     */
    IShip fire(IPosition pos);

    /**
     * Devolve todas as posições onde já foram efetuados disparos.
     *
     * @return lista de posições disparadas
     */
    List<IPosition> getShots();

    /**
     * Devolve o número de disparos repetidos.
     *
     * @return número de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Devolve o número de disparos inválidos.
     *
     * @return número de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Devolve o número de disparos certeiros.
     *
     * @return número de acertos
     */
    int getHits();

    /**
     * Devolve o número de navios afundados.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Devolve o número de navios ainda por afundar.
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime os disparos válidos efetuados.
     */
    void printValidShots();

    /**
     * Imprime o estado atual da frota.
     */
    void printFleet();
}

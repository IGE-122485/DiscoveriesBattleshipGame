package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa a frota de navios do jogo Battleship.
 * Permite gerir e consultar os navios colocados no tabuleiro.
 */
public interface IFleet {

    /** Tamanho do tabuleiro (10x10). */
    Integer BOARD_SIZE = 10;

    /** Número máximo de navios na frota. */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve todos os navios da frota.
     *
     * @return lista de navios
     */
    List<IShip> getShips();

    /**
     * Adiciona um navio à frota.
     *
     * @param s navio a adicionar
     * @return true se o navio foi adicionado com sucesso; false caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Obtém os navios da categoria indicada.
     *
     * @param category categoria a procurar
     * @return lista de navios dessa categoria
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve os navios que ainda não foram afundados.
     *
     * @return lista de navios a flutuar
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio que ocupa a posição indicada.
     *
     * @param pos posição a verificar
     * @return navio presente na posição ou null se não existir
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime o estado atual da frota.
     */
    void printStatus();
}

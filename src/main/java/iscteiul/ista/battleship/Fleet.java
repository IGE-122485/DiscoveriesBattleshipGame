package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma frota de navios no jogo Batalha Naval.
 * Responsável por gerir a coleção de navios de um jogador, garantindo que
 * são colocados dentro dos limites do tabuleiro e sem colisões entre si.
 */
public class Fleet implements IFleet {
    
    /**
     * Método auxiliar que imprime na consola a informação de uma lista de navios.
     *
     * @param ships A lista de navios a ser impressa.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Construtor da classe Fleet.
     * Inicializa a frota com uma lista vazia de navios.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Obtém a lista completa de navios que compõem esta frota.
     *
     * @return Uma lista com todos os navios (IShip) da frota.
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um novo navio à frota.
     * O navio apenas é adicionado se a frota ainda não tiver atingido o tamanho máximo,
     * se o navio estiver totalmente dentro dos limites do tabuleiro e se não 
     * colidir com outros navios já existentes.
     *
     * @param s O navio a ser adicionado à frota.
     * @return true se o navio foi adicionado com sucesso, false caso contrário.
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Obtém uma lista de navios pertencentes a uma categoria específica.
     *
     * @param category A categoria dos navios a procurar (ex: "Galeao", "Fragata").
     * @return Uma lista contendo apenas os navios dessa categoria.
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Obtém uma lista com todos os navios da frota que ainda estão a flutuar 
     * (ou seja, que ainda não foram totalmente afundados).
     *
     * @return Uma lista de navios a flutuar.
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Procura e devolve o navio que ocupa uma determinada posição no tabuleiro.
     *
     * @param pos A posição a verificar.
     * @return O navio que ocupa a posição fornecida, ou null se a posição estiver vazia.
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um determinado navio se encontra inteiramente dentro dos limites do tabuleiro.
     *
     * @param s O navio a verificar.
     * @return true se o navio estiver dentro do tabuleiro, false caso contrário.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se existe risco de colisão entre um novo navio e os navios já presentes na frota.
     *
     * @param s O navio a verificar.
     * @return true se houver risco de colisão (ou proximidade excessiva), false se for seguro colocar o navio.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Imprime na consola o estado atual e geral da frota.
     * Mostra todos os navios, os que estão a flutuar e a distribuição por categorias.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime na consola todos os navios da frota que pertencem a uma categoria específica.
     *
     * @param category A categoria de navios que se pretende visualizar.
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime na consola apenas os navios da frota que ainda não foram afundados.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime na consola todos os navios que compõem a frota.
     */
    void printAllShips() {
        printShips(ships);
    }

}

package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gere o estado de uma partida do jogo Batalha Naval.
 * Responsável por controlar a frota, processar os tiros disparados e
 * contabilizar as estatísticas do jogo (tiros inválidos, repetidos, acertos e afundamentos).
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Construtor da classe Game.
     * Inicializa as listas e os contadores base para uma nova partida.
     *
     * @param fleet A frota de navios a ser utilizada nesta partida.
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Processa um tiro disparado contra uma posição do tabuleiro.
     * Verifica se o tiro é válido, repetido, ou se acertou num navio,
     * atualizando os respetivos contadores.
     *
     * @param pos A posição alvo do tiro.
     * @return O navio caso este tiro o tenha afundado, ou null caso contrário.
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obtém a lista de todas as posições onde já foram disparados tiros.
     *
     * @return Uma lista com as posições dos tiros.
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Obtém o número total de tiros repetidos efetuados durante a partida.
     *
     * @return O número de tiros repetidos.
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Obtém o número total de tiros inválidos efetuados durante a partida.
     *
     * @return O número de tiros inválidos.
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Obtém o número total de tiros que acertaram com sucesso em navios.
     *
     * @return O número de tiros certeiros (acertos).
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Obtém o número total de navios que já foram afundados.
     *
     * @return O número de navios afundados.
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Obtém o número de navios que ainda não foram totalmente afundados.
     *
     * @return O número de navios restantes na frota.
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se a posição de um tiro está dentro dos limites do tabuleiro.
     *
     * @param pos A posição do tiro a verificar.
     * @return true se o tiro for válido (dentro do tabuleiro), false caso contrário.
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um tiro já foi disparado anteriormente para a mesma posição.
     *
     * @param pos A posição do tiro a verificar.
     * @return true se a posição já tiver sido alvo de um tiro, false caso contrário.
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Método auxiliar genérico que imprime o tabuleiro de jogo na consola,
     * marcando posições específicas com o caracter indicado.
     *
     * @param positions A lista de posições a marcar no tabuleiro.
     * @param marker O caracter a ser utilizado para a marcação.
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Imprime o tabuleiro de jogo mostrando todos os tiros válidos que já foram disparados.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro de jogo mostrando a disposição de todos os navios da frota.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}

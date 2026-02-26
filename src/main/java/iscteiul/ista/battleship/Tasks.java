package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilitária que contém várias tarefas (tasks) de teste para o desenvolvimento 
 * incremental do jogo da Batalha Naval.
 * Permite testar a criação de navios, frotas, comandos de jogo e a mecânica de disparos.
 */
public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int NUMBER_SHOTS = 3;

    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Strings utilizadas para os comandos introduzidos pelo utilizador.
     */
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";

    /////////////////////////////////////////////////////////////////////////////
    // A partir daqui encontra-se código que pode ser convertido em testes automáticos,
    // desde que sejam feitas as alterações adequadas. Mostra também que devemos
    // desenvolver o código incrementalmente: primeiro navios, depois frota, 
    // depois regras, depois disparos, etc.
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Tarefa A: Testa a construção de navios.
     * Para cada navio criado, lê posições e indica se o navio ocupa ou não essas posições.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Tarefa B: Testa a construção de frotas e a verificação do seu estado atual.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // Os outros comandos são desconhecidos nesta tarefa
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa C: Testa a construção de frotas, estado e introduz a possibilidade
     * de fazer "batota" (imprimir a disposição dos navios no mapa).
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // Os outros comandos são desconhecidos nesta tarefa
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa D: Testa os elementos de combate, nomeadamente as rajadas de tiros,
     * num ciclo contínuo de jogo.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Permite a construção de uma frota com base nos dados introduzidos pelo utilizador.
     *
     * @param in O Scanner utilizado para ler as entradas do utilizador.
     * @return A frota construída e preenchida com os navios validados.
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i representa o total de navios criados com sucesso

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê os dados sobre um navio (tipo, posição, orientação), constrói-o e devolve-o.
     *
     * @param in O Scanner utilizado para ler as entradas do utilizador.
     * @return O navio criado com base nos dados lidos, ou null se o tipo for inválido.
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição (coordenada) do mapa introduzida pelo utilizador.
     *
     * @param in O Scanner utilizado para ler a linha e a coluna.
     * @return A posição (objeto Position) correspondente.
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Permite disparar uma rajada de tiros (três disparos) sobre uma frota, 
     * no contexto de um jogo a decorrer.
     *
     * @param in   O Scanner utilizado para ler as coordenadas dos tiros.
     * @param game O contexto do jogo atual onde a frota está a ser atacada.
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}

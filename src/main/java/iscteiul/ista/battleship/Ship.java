package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que representa um navio genérico no jogo da Batalha Naval.
 * Fornece a base para os diferentes tipos de navios (Galeão, Fragata, Nau, Caravela, Barca),
 * gerindo o seu estado, posições ocupadas e as verificações de proximidade no tabuleiro.
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método fábrica (Factory) estático que constrói e devolve uma instância específica 
     * de um navio com base na categoria fornecida.
     *
     * @param shipKind A categoria do navio (ex: "galeao", "caravela").
     * @param bearing  A orientação do navio (ex: Norte, Sul, Este, Oeste).
     * @param pos      A posição inicial (referência) do navio no tabuleiro.
     * @return Uma instância da subclasse correspondente ao tipo de navio, ou null se o tipo for inválido.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Construtor da classe base Ship.
     * Inicializa as propriedades comuns a todos os navios.
     *
     * @param category A categoria/tipo do navio.
     * @param bearing  A orientação do navio. Não pode ser nulo.
     * @param pos      A posição de referência inicial. Não pode ser nulo.
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria ou tipo do navio.
     *
     * @return Uma string que representa a categoria do navio.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista de todas as posições do tabuleiro que este navio ocupa.
     *
     * @return Uma lista de objetos IPosition.
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição principal (de referência/inicial) do navio.
     *
     * @return A posição base do navio.
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação do navio no tabuleiro.
     *
     * @return O compasso (bearing) que indica para onde o navio está virado.
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda está a flutuar (ou seja, se nem todas as suas posições foram atingidas).
     *
     * @return true se o navio tiver pelo menos uma posição intacta, false se estiver totalmente afundado.
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém o valor da linha mais acima que o navio ocupa no tabuleiro.
     *
     * @return O menor índice de linha ocupado por este navio.
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém o valor da linha mais abaixo que o navio ocupa no tabuleiro.
     *
     * @return O maior índice de linha ocupado por este navio.
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém o valor da coluna mais à esquerda que o navio ocupa no tabuleiro.
     *
     * @return O menor índice de coluna ocupado por este navio.
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém o valor da coluna mais à direita que o navio ocupa no tabuleiro.
     *
     * @return O maior índice de coluna ocupado por este navio.
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se este navio ocupa uma determinada posição no tabuleiro.
     *
     * @param pos A posição a verificar.
     * @return true se o navio estiver nessa posição, false caso contrário.
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado perto de outro navio (tocando-se ou sobrepondo-se),
     * violando as regras de posicionamento do jogo.
     *
     * @param other O outro navio a ser comparado.
     * @return true se os navios estiverem demasiado perto, false se houver distância segura.
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se alguma parte deste navio está adjacente ou sobreposta a uma dada posição.
     *
     * @param pos A posição a verificar.
     * @return true se o navio estiver demasiado perto da posição, false caso contrário.
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Regista um tiro neste navio. Se a posição fornecida corresponder a uma das
     * posições ocupadas pelo navio, essa posição é marcada como atingida.
     *
     * @param pos A posição do tiro disparado.
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Retorna uma representação textual do navio, útil para depuração (debug).
     *
     * @return Uma string contendo a categoria, orientação e posição base do navio.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}

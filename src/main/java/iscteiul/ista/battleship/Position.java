package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição (coordenada) específica no tabuleiro do jogo da Batalha Naval.
 * Guarda a informação sobre a sua localização (linha e coluna), se está ocupada por um navio
 * e se já foi atingida por um tiro.
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Construtor que inicializa uma nova posição no tabuleiro.
     * Por omissão, a posição começa desocupada e sem nenhum tiro registado.
     *
     * @param row    A linha da coordenada (ex: 0 a 9).
     * @param column A coluna da coordenada (ex: 0 a 9).
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Obtém a linha atual desta posição.
     *
     * @return O valor inteiro correspondente à linha.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Obtém a coluna atual desta posição.
     *
     * @return O valor inteiro correspondente à coluna.
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera um código hash para a posição com base nos seus atributos.
     * * @return O código hash gerado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto para verificar se são iguais.
     * Duas posições são consideradas iguais se tiverem exatamente a mesma linha e coluna.
     *
     * @param otherPosition O outro objeto a comparar.
     * @return true se as posições forem idênticas, false caso contrário.
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente a uma outra posição fornecida.
     * A adjacência inclui posições horizontais, verticais e diagonais diretas.
     *
     * @param other A outra posição a verificar.
     * @return true se for adjacente, false caso contrário.
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como estando ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Regista um tiro nesta posição, marcando-a como atingida.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Verifica se a posição está atualmente ocupada por um navio.
     *
     * @return true se estiver ocupada, false caso contrário.
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Verifica se a posição já foi alvo de um tiro durante o jogo.
     *
     * @return true se já foi atingida, false caso contrário.
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Retorna uma representação textual da posição para facilitar a visualização e testes.
     *
     * @return Uma string contendo a linha e a coluna (ex: "Linha = 0 Coluna = 0").
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}

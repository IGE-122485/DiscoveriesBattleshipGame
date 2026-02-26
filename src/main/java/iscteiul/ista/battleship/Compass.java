/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa as orientações geográficas para o posicionamento dos navios no tabuleiro.
 * <p>
 * Este enumerador define as quatro direções cardeais principais e um estado de 
 * direção desconhecida. Importa notar que o mapeamento interno de caracteres 
 * utiliza iniciais em português para o Oeste ('o') e não a notação inglesa ('w').
 * </p>
 *
 * @author fba
 * @author O Seu Nome / A Sua Equipa
 * @version 1.0
 */
public enum Compass {
    
    /** Orientação a Norte (representada pelo caractere 'n'). */
    NORTH('n'), 
    
    /** Orientação a Sul (representada pelo caractere 's'). */
    SOUTH('s'), 
    
    /** Orientação a Este (representada pelo caractere 'e'). */
    EAST('e'), 
    
    /** Orientação a Oeste (representada pelo caractere 'o' - Oeste). */
    WEST('o'), 
    
    /** Orientação desconhecida ou inválida (representada pelo caractere 'u'). */
    UNKNOWN('u');

    /** O caractere único e imutável que representa a direção. */
    private final char c;

    /**
     * Construtor privado do enumerador Compass.
     *
     * @param c O caractere associado à direção cardeal.
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o caractere representativo da direção deste enumerador.
     *
     * @return O caractere correspondente à orientação (ex: 'n', 's', 'e', 'o', 'u').
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação textual do caractere da direção.
     *
     * @return Uma string contendo unicamente o caractere da direção.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caractere na sua constante {@link Compass} correspondente.
     *
     * @param ch O caractere a ser convertido.
     * @return A constante {@link Compass} equivalente ao caractere introduzido, ou 
     * {@link #UNKNOWN} se o caractere não for suportado pela bússola.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}

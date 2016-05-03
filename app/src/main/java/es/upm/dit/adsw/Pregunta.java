package es.upm.dit.adsw;

/**
 * Created by cif on 3/05/16.
 */
public class Pregunta {
    private String texto;
    private boolean resultado;


    public Pregunta(String texto, boolean resultado) {
        this.texto = texto;
        this.resultado = resultado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
    @Override
    public String toString() {
        return "Pregunta{" +
                "texto='" + texto + '\'' +
                ", resultado=" + resultado +
                '}';
    }
}

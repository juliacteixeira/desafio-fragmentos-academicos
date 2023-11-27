
/**
* Interface que contém métodos para obter informações sobre o desempenho do jogador.
*
* @author  João Dias
* @version 2023.11.27
*/
import java.util.List;

public interface RelatorioDesempenho {
    List<Pendrive> getPendrivesColetados();
    int getTempoGasto();
}




package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de débito de conta")
public class TesteDebitoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;


    @BeforeEach
    void preparar(){
        contaValida = new Conta(1,cem,"Rebecca");
    }


    @Test
    @DisplayName("Válida debito nulo como obrtigatório")
    void teste1() {
        try {
            contaValida.debitar(null);
            fail("Valor débito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida debito negativo como obrtigatório")
    void teste2() {
        try {
            contaValida.debitar(new BigDecimal(-10));
            fail("Valor débito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida debito zero como obrtigatório")
    void teste3() {
        try {
            contaValida.debitar(BigDecimal.ZERO);
            fail("Valor débito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida debito acima do saldo")
    void teste4() {
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("Valor débito acima do saldo.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Saldo insuficiente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida debito igual do saldo")
    void teste5() {
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(),BigDecimal.ZERO,"Saldo deve zerar.");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage() );
        }
    }

    @Test
    @DisplayName("Válida debito menor que o saldo")
    void teste6() {
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(),saldoFinal,"Saldo deve bater.");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage() );
        }
    }
}

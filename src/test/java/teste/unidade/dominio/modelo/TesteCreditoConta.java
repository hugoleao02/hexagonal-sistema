package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de crédito de conta")
public class TesteCreditoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;


    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Rebecca");
    }

    @Test
    @DisplayName("Válida credito nulo como obrtigatório")
    void teste1() {
        try {
            contaValida.creditar(null);
            fail("Valor crédito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida credito negativo como obrtigatório")
    void teste2() {
        try {
            contaValida.creditar(new BigDecimal(-10));
            fail("Valor crédito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida credito zero como obrtigatório")
    void teste3() {
        try {
            contaValida.creditar(new BigDecimal(0));
            fail("Valor crédito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Válida credito acima zero como obrtigatório")
    void teste4() {
        try {
            contaValida.creditar(BigDecimal.ONE);
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater.");
        } catch (NegocioException e) {
            fail("Deve créditar com sucesso - " + e.getMessage());
        }
    }
}

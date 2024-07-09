module conta.sistema {

    requires javax.inject;

    requires spring.tx;

    // expondo porta de entrada (driver)
    exports conta.sistema.casouso.imp;
    exports conta.sistema.casouso.porta;

    // expondo sistema negocio
    exports conta.sistema.dominio.servico;
    exports conta.sistema.dominio.modelo;

    // expondo adaptadores de saídas (driven)
    exports conta.adaptador;
    exports conta.sistema.porta;

    opens conta.sistema.casouso.porta;
    opens conta.sistema.casouso.imp;
    opens conta.sistema.casouso.servico;
    opens conta.adaptador;

    exports teste.unidade.dominio.modelo;

}
package br.com.alura.Busca;

import br.com.alura.BuscaRecord.BuscaRecord;

public class Busca {
    private String rua;
    private String bairro;
    private String estado;

    public Busca(BuscaRecord busca){
        this.estado = busca.estado();
        this.rua = busca.logradouro();
        this.bairro = busca.bairro();
    }

    @Override
    public String toString() {
        return "Rua: " + rua + " | Bairro: " + bairro + " | Estado: " + estado;
    }
}

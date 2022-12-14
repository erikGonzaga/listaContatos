package com.gonzaga.contatos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

// Mapeamento objetos no Banco de Dados
// Anotando esta classe como uma entidade com @Entity.
// Marcando a calsse com nome da tabela com @Table

@Data
@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    // Anotando os valores da classe com os nomes da tabela.
    @Id // Definindo Primary Key (PK)
    private String id;

    // ReferĂȘnciando nomes das colunas,
    // indicando nome das colunas, tamanho e se not null
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "documento", nullable = false)
    private Long documento;

    public Contato(){
        ativo = true;
    }
}

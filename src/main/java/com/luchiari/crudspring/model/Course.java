package com.luchiari.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data //Notacao responsavel por gerar os Getter e Setter e demais funções da classe
@Entity //Marca essa classe como uma entidade que será mapeada no BD
//@Table( name = "cursos" ) - Indica o nome da tabela no BD
public class Course {
    
    @Id //Indica que é uma chave primaria
    @GeneratedValue( strategy = GenerationType.AUTO ) //Indica que o campo sera preenchido automaticamente, e define a estrategia utilizada para este preenchimento
    @JsonProperty("_id") //Quando a biblioteca Jackson for transformar o objeto em um Json para retornar para a web, ira utilizar o nome definido nesta notacao para a propriedade id
    private Long id;

    @Column( length = 200, nullable = false ) //Indica que e uma coluna da tabela, não é obrigatorio, ficara implicito se não for passado
    // @Column( name = "nome" ) - E possivel indicar o nome da coluna que esta amarrada a esta propriedade
    //length - Indica o tamanho do campo na tabela
    //nullable - Indica se o campo podera ser nulo
    private String name;

    @Column( length = 10, nullable = false )
    private String category;

    //Quanto mais informacoes sobre as colunas da tabela forem adicionadas
    //melhor sera a geracao do SQL final
}

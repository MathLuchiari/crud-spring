package com.luchiari.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data //Notacao responsavel por gerar os Getter e Setter e demais funções da classe
@Entity //Marca essa classe como uma entidade que será mapeada no BD
//@Table( name = "cursos" ) - Indica o nome da tabela no BD
@SQLDelete(sql = "UPDATE Course SET status = 'inativo' WHERE id = ?")
// NOTE Deprecated - @Where(clause = "status = 'ativo'")
@SQLRestriction("status = 'ativo'")
public class Course {
    
    @Id //Indica que é uma chave primaria
    @GeneratedValue( strategy = GenerationType.AUTO ) //Indica que o campo sera preenchido automaticamente, e define a estrategia utilizada para este preenchimento
    @JsonProperty("_id") //Quando a biblioteca Jackson for transformar o objeto em um Json para retornar para a web, ira utilizar o nome definido nesta notacao para a propriedade id
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column( length = 100, nullable = false ) //Indica que e uma coluna da tabela, não é obrigatorio, ficara implicito se não for passado
    // @Column( name = "nome" ) - E possivel indicar o nome da coluna que esta amarrada a esta propriedade
    //length - Indica o tamanho do campo na tabela
    //nullable - Indica se o campo podera ser nulo
    private String name;

    @NotNull
    @Length( max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column( length = 10, nullable = false )
    private String category;

    @NotNull
    @Length( max = 10)
    @Pattern(regexp = "ativo|inativo")
    @Column( length = 10, nullable = false )
    private String status = "ativo";

    //Quanto mais informacoes sobre as colunas da tabela forem adicionadas
    //melhor sera a geracao do SQL final
}

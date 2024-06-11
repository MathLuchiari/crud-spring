package com.luchiari.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luchiari.crudspring.enums.Category;
import com.luchiari.crudspring.enums.Status;
import com.luchiari.crudspring.enums.converters.CategoryConverter;
import com.luchiari.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
    @Column( length = 10, nullable = false )
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column( length = 10, nullable = false )
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    // NOTE @JoinColumn(name = "course_id") -> Não é uma boa prática
    // pois na criação dos registros executa 3 querys
    // 1 INSERT INTO na tabela pai
    // 1 INSERT INTO na tabela filha
    // 1 UPDATE na tabela filha
    // Podendo gerar lentidão quando em larga escala
    // Em relacionamentos OneToMany no Spring sempre dê preferencia para relações bidirecionais 
    // como demonstrado no aquivo abaixo
    // LINK ./Lesson.java#relacao_bidirecional_spring
    @NotNull
    @NotEmpty
    @Valid
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank @NotNull String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(@NotNull @NotEmpty @Valid List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", category=" + category + ", status=" + status + ", lessons="
                + lessons + "]";
    }
    
    //Quanto mais informacoes sobre as colunas da tabela forem adicionadas
    //melhor sera a geracao do SQL final
}

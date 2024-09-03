package spring.boot.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Position should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String position;

    @PositiveOrZero
    @NotNull(message = "Salary should not be null")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne
    @JsonIgnore
    private User user;
}

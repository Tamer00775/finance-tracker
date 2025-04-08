package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget_planning")
@Getter
@Setter
@ToString
public class BudgetPlanning {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_planning_id_seq")
    @SequenceGenerator(name = "budget_planning_id_seq", sequenceName = "budget_planning_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "plan_sum")
    private Long planSum;

    @Column(name = "current_sum")
    private Long currentSum;

    @Column(name = "percentage")
    private Double percentage;
    @Column(name = "expiration_date")
    private LocalDateTime createdDate;

    @Column(name = "source")
    private String source;
}

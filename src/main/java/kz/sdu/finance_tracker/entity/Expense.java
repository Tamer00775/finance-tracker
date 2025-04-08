package kz.sdu.finance_tracker.entity;

import kz.sdu.finance_tracker.enums.OperationType;
import kz.sdu.finance_tracker.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
@Getter
@Setter
@ToString
public class Expense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_seq")
    @SequenceGenerator(name = "expense_id_seq", sequenceName = "expense_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "operation_type")
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
    @Column(name = "transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = "sum")
    private Double sum;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

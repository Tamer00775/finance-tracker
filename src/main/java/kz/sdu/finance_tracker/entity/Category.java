package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "category")
@Entity
@Getter
@Setter
@ToString
public class Category {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "ru")
    private String ru;
    @Column(name = "kk")
    private String kk;
    @Column(name = "type")
    private String type;

}

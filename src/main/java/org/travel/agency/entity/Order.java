package org.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_till")
    private LocalDate dateTill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
                referencedColumnName = "id",
                nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id",
                referencedColumnName = "id",
                nullable = false)
    private Room room;

}

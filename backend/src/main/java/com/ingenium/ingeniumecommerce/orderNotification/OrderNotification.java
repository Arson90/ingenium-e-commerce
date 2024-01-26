package com.ingenium.ingeniumecommerce.orderNotification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_notification")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private Long orderId;
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Setter
    private OrderNotificationStatus status;
    @Setter
    private boolean isLock;
    @Setter
    private int sendingAttempts;

    public void addSendingAttempts() {
        this.sendingAttempts ++;
    }
}

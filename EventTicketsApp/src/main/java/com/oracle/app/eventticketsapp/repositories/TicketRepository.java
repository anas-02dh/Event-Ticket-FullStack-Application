package com.oracle.app.eventticketsapp.repositories;

import com.oracle.app.eventticketsapp.entities.Event;
import com.oracle.app.eventticketsapp.entities.Ticket;
import com.oracle.app.eventticketsapp.entities.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
public interface TicketRepository extends JpaRepository<Ticket,String> {
    List<Ticket> findByEvent(Event event);

    List<Ticket> findByUser(User user);

    List<Ticket> findTop5ByOrderByPurchaseDateDesc();

    void deleteAllByUser(User user);

    void deleteAllByEvent(Event event);
}

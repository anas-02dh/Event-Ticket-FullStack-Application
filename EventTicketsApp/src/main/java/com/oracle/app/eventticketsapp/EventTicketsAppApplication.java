package com.oracle.app.eventticketsapp;

import com.oracle.app.eventticketsapp.entities.Category;
import com.oracle.app.eventticketsapp.entities.Event;
import com.oracle.app.eventticketsapp.entities.Ticket;
import com.oracle.app.eventticketsapp.entities.User;
import com.oracle.app.eventticketsapp.enums.EventStatus;
import com.oracle.app.eventticketsapp.enums.TicketStatus;
import com.oracle.app.eventticketsapp.enums.UserRole;
import com.oracle.app.eventticketsapp.repositories.CategoryRepository;
import com.oracle.app.eventticketsapp.repositories.EventRepository;
import com.oracle.app.eventticketsapp.repositories.TicketRepository;
import com.oracle.app.eventticketsapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class EventTicketsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventTicketsAppApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            EventRepository eventRepository,
            TicketRepository ticketRepository) {

        return args -> {


            // Categories


            Category tech = new Category();
            tech.setName("Technology");
            tech.setDescription("Technology conferences");
            categoryRepository.save(tech);

            Category music = new Category();
            music.setName("Music");
            music.setDescription("Music concerts");
            categoryRepository.save(music);

            Category business = new Category();
            business.setName("Business");
            business.setDescription("Business events");
            categoryRepository.save(business);

            Category education = new Category();
            education.setName("Education");
            education.setDescription("Training and workshops");
            categoryRepository.save(education);

            Category sports = new Category();
            sports.setName("Sports");
            sports.setDescription("Sports competitions");
            categoryRepository.save(sports);


            // Admins (Organizers)


            User admin1 = new User();
            admin1.setName("John Smith");
            admin1.setEmail("john@events.com");
            admin1.setPassword("123456");
            admin1.setPhone("0611111111");
            admin1.setRole(UserRole.ADMIN);
            admin1.setCreatedAt(LocalDateTime.now());
            userRepository.save(admin1);

            User admin2 = new User();
            admin2.setName("Sarah Johnson");
            admin2.setEmail("sarah@events.com");
            admin2.setPassword("123456");
            admin2.setPhone("0622222222");
            admin2.setRole(UserRole.ADMIN);
            admin2.setCreatedAt(LocalDateTime.now());
            userRepository.save(admin2);


            // Customers


            List<User> customers = new ArrayList<>();

            Stream.of(
                    "Anas",
                    "Ahmed",
                    "Fatima",
                    "Youssef",
                    "Salma",
                    "Omar"
            ).forEach(name -> {

                User customer = new User();
                customer.setName(name);
                customer.setEmail(name.toLowerCase() + "@gmail.com");
                customer.setPassword("123456");
                customer.setPhone("0600000000");
                customer.setRole(UserRole.CUSTOMER);
                customer.setCreatedAt(LocalDateTime.now());

                userRepository.save(customer);

                customers.add(customer);
            });


            // Events


            List<Event> events = new ArrayList<>();

            Event event1 = new Event();
            event1.setTitle("Spring Boot Conference");
            event1.setDescription("Enterprise Spring Boot");
            event1.setDate(LocalDate.of(2026, 8, 12));
            event1.setTime(LocalTime.of(9, 0));
            event1.setLocation("Casablanca");
            event1.setPrice(350);
            event1.setCapacity(100);
            event1.setRemainSeats(100);
            event1.setStatus(EventStatus.OnSale);
            event1.setCategory(tech);
            event1.setOrganizer(admin1);
            eventRepository.save(event1);
            events.add(event1);

            Event event2 = new Event();
            event2.setTitle("Angular Summit");
            event2.setDescription("Angular & Tailwind CSS");
            event2.setDate(LocalDate.of(2026, 8, 20));
            event2.setTime(LocalTime.of(10, 0));
            event2.setLocation("Rabat");
            event2.setPrice(300);
            event2.setCapacity(120);
            event2.setRemainSeats(120);
            event2.setStatus(EventStatus.OnSale);
            event2.setCategory(tech);
            event2.setOrganizer(admin2);
            eventRepository.save(event2);
            events.add(event2);

            Event event3 = new Event();
            event3.setTitle("Docker Workshop");
            event3.setDescription("Docker & Kubernetes");
            event3.setDate(LocalDate.of(2026, 9, 5));
            event3.setTime(LocalTime.of(9, 30));
            event3.setLocation("Casablanca");
            event3.setPrice(400);
            event3.setCapacity(80);
            event3.setRemainSeats(80);
            event3.setStatus(EventStatus.OnSale);
            event3.setCategory(education);
            event3.setOrganizer(admin1);
            eventRepository.save(event3);
            events.add(event3);

            // ==========================
            // Tickets
            // ==========================

            Random random = new Random();

            for (User customer : customers) {

                for (int i = 0; i < 2; i++) {

                    Event event = events.get(random.nextInt(events.size()));

                    int quantity = random.nextInt(3) + 1;

                    if (event.getRemainSeats() >= quantity) {

                        Ticket ticket = new Ticket();

                        ticket.setUser(customer);
                        ticket.setEvent(event);
                        ticket.setPurchaseDate(LocalDate.now().minusDays(random.nextInt(30)));
                        ticket.setQuantity(quantity);
                        ticket.setStatus(TicketStatus.CONFIRMED);
                        ticket.setTotalPrice(quantity * event.getPrice());

                        event.setRemainSeats(event.getRemainSeats() - quantity);

                        ticketRepository.save(ticket);
                    }
                }
            }

            eventRepository.saveAll(events);

        };
    }

}
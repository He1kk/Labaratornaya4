package ru.bsuedu.cad.lab.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bsuedu.cad.lab.entity.Order;
import ru.bsuedu.cad.lab.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        System.out.println("[LOG TRANSACT] Order #" + savedOrder.getOrderId() + " successfully created and committed.");
        return savedOrder;
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
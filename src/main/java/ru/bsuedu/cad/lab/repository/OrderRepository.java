package ru.bsuedu.cad.lab.repository;
import org.springframework.data.repository.CrudRepository;
import ru.bsuedu.cad.lab.entity.Order;
public interface OrderRepository extends CrudRepository<Order, Integer> {}
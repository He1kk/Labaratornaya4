package ru.bsuedu.cad.lab.repository;
import org.springframework.data.repository.CrudRepository;
import ru.bsuedu.cad.lab.entity.OrderDetail;
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {}
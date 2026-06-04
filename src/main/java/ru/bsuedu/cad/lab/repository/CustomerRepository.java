package ru.bsuedu.cad.lab.repository;
import org.springframework.data.repository.CrudRepository;
import ru.bsuedu.cad.lab.entity.Customer;
public interface CustomerRepository extends CrudRepository<Customer, Integer> {}
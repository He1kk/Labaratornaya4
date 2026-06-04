package ru.bsuedu.cad.lab.repository;
import org.springframework.data.repository.CrudRepository;
import ru.bsuedu.cad.lab.entity.Category;
public interface CategoryRepository extends CrudRepository<Category, Integer> {}
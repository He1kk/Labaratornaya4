package ru.bsuedu.cad.lab.app;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.bsuedu.cad.lab.entity.*;
import ru.bsuedu.cad.lab.repository.*;
import ru.bsuedu.cad.lab.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "ru.bsuedu.cad.lab")
@EnableJpaRepositories(basePackages = "ru.bsuedu.cad.lab.repository")
@EntityScan(basePackages = "ru.bsuedu.cad.lab.entity")
public class Main implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    public Main(CustomerRepository customerRepository,
                CategoryRepository categoryRepository,
                ProductRepository productRepository,
                OrderService orderService) {
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- Initializing reference data (Simulating CSV import) ---");

        Customer customer = new Customer("Alex Ivanov", "ivanov@mail.ru", "+79001234567", "Belgorod, Pobedy st. 85");
        customerRepository.save(customer);

        Category category = new Category("Auto Parts", "Car maintenance components");
        categoryRepository.save(category);

        Product product = new Product("Brake Discs Vesta", "Front brake discs kit",
                category, new BigDecimal("4500.00"), 20, "http://example.com/img.jpg");
        productRepository.save(product);

        System.out.println("Reference data successfully saved to H2 database.");
        System.out.println("\n--- Creating a customer order (Calling Transactional Service) ---");

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("NEW");
        order.setShippingAddress(customer.getAddress());
        order.setTotalPrice(new BigDecimal("9000.00")); // 2 items x 4500.00
        OrderDetail detail = new OrderDetail();
        detail.setProduct(product);
        detail.setQuantity(2);
        detail.setPrice(product.getPrice());
        order.addOrderDetail(detail);

        orderService.createOrder(order);

        System.out.println("\n--- Verification: Checking saved order in Database ---");
        Iterable<Order> ordersInDb = orderService.getAllOrders();
        for (Order o : ordersInDb) {
            System.out.println("[DB SUCCESS] Found Order ID: " + o.getOrderId() +
                    " | Customer: " + o.getCustomer().getName() +
                    " | Total Price: " + o.getTotalPrice() + " RUB");
            for (OrderDetail od : o.getOrderDetails()) {
                System.out.println("   -> Item: " + od.getProduct().getName() + " x" + od.getQuantity());
            }
        }
        System.out.println("\nApplication completed all steps successfully.");
    }
}
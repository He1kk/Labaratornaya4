## Лабораторная работа 4. Технологии работы с базами данных. JPA. Spring Data

## Цель работы
Переход с использования Spring JDBC на ORM Hibernate и Spring Data JPA, расширение приложения новыми сущностями и приведение структуры к многослойной архитектуре.

## Описание реализации
Настроена встроенная база данных H2.
Схема данных автоматически генерируется на основе JPA-аннотаций сущностей.
**Приложение разделено на четкие пакеты:**
   * `entity` - сущности (`Customer`, `Category`, `Product`, `Order`, `OrderDetail`) с настроенными связями `@ManyToOne` и `@OneToMany`.
   * `repository` - интерфейсы
   * `service` - бизнес-логика
   * `app` - точка входа (клиент)
 **Транзакционность:** Метод создания заказа аннотирован `@Transactional`, что гарантирует атомарность операции (сохранение заказа и его позиций происходит в единой транзакции).

## UML-диаграмма классов проекта

```mermaid
classDiagram
    class Customer {
        +Integer customerId
        +String name
        +String email
        +String phone
        +String address
    }

    class Category {
        +Integer categoryId
        +String name
        +String description
    }

    class Product {
        +Integer productId
        +String name
        +String description
        +Category category
        +BigDecimal price
        +Integer stockQuantity
        +String imageUrl
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
    }

    class Order {
        +Integer orderId
        +Customer customer
        +LocalDateTime orderDate
        +BigDecimal totalPrice
        +String status
        +String shippingAddress
        +List~OrderDetail~ orderDetails
    }

    class OrderDetail {
        +Integer orderDetailId
        +Order order
        +Product product
        +Integer quantity
        +BigDecimal price
    }

    Customer "1" --> "*" Order : размещает
    Category "1" --> "*" Product : содержит
    Order "1" *-- "*" OrderDetail : содержит
    Product "1" --> "*" OrderDetail : включен в

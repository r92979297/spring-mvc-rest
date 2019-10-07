package com.fafik.bootstrap;

import com.fafik.domain.Category;
import com.fafik.domain.Customer;
import com.fafik.repositories.CategoryRepository;
import com.fafik.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner
{

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args){
        initCategories();

        initCustomers();
    }

    private void initCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Tim");
        customer1.setLastName("Cook");

        Customer customer2 = new Customer();
        customer2.setFirstName("John");
        customer2.setLastName("Snow");

        Customer customer3 = new Customer();
        customer3.setFirstName("Alice");
        customer3.setLastName("Cooper");

        Customer customer4 = new Customer();
        customer4.setFirstName("Marylin");
        customer4.setLastName("Manson");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        System.out.println("Customers Data Loaded = " +customerRepository.count());
    }

    private void initCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Data Loaded = " +categoryRepository.count());
    }

}

package org.foo.ffk;

import com.github.javafaker.Faker;
import org.foo.ffk.enums.Gender;
import org.foo.ffk.model.Customer;
import org.foo.ffk.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class FfkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FfkApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Faker faker = new Faker();
            Random random = new Random();
            var name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            Customer customer = new Customer(
                    firstName + " " + lastName,
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com",
                    passwordEncoder.encode("password"), random.nextInt(18, 65),
                    Gender.NAN);
            customerRepository.save(customer);
        };
    }
}

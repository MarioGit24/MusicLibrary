package se.yrgo.config;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Configuration
public class InitialDataConfig {

    @Bean
    CommandLineRunner initDatabase(RecordlabelRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Recordlabel label = new Recordlabel();
                label.setName("Republic Records");

                repository.save(label);
            }
        };
    }
}
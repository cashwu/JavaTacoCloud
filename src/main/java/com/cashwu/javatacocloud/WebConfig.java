package com.cashwu.javatacocloud;

import com.cashwu.javatacocloud.model.TacoOrder;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("home");
        registry.addViewController("/login");

    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.bean-queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {

        var converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");

        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order",
                           TacoOrder.class);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }

    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    //  @Bean
    //  @Profile({"dev", "!prod"})
    //  public CommandLineRunner dataLoader(IngredientRepository repo) {
    //    return args -> {
    //      repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
    //      repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
    //      repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
    //      repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
    //      repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
    //      repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
    //      repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
    //      repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
    //      repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
    //      repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
    //      repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    //    };
    //  }

}

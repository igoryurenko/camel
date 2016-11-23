package com.iyurenko.config;

import com.iyurenko.dao.domain.Thing;
import com.iyurenko.dao.repository.ThingRepository;
import com.iyurenko.service.MessageProcessService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Created by iyurenko on 22.11.16.
 */
@Component
public class RestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("jetty").port(8082).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest("/thing")
                .post()
                    .type(Thing.class)
                    .to("direct:createThing")
                .delete("/{id}")
                    .outType(Thing.class)
                    .to("direct:removeThing");

        rest("/log")
                .get()
                    .outType(String.class)
                    .to("direct:log");


        from("direct:createThing")
                .bean("thingRepository", "save(${body})")
                .bean("messageProcessService", "sendAddMessage(${body})");


        from("direct:log")
                .bean("messageProcessService", "log");

    }

}

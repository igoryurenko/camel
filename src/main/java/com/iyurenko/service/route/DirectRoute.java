package com.iyurenko.service.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by iyurenko on 25.11.16.
 */
@Component
public class DirectRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:createThing")
                .bean("thingRepository", "save(${body})")
                .bean("messageProcessService", "sendAddMessage(${body})")
                .to("direct:log");

        from("direct:log")
                .bean("messageProcessService", "log");
    }
}

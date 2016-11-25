package com.iyurenko.service.route;

import com.iyurenko.dao.domain.Thing;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

/**
 * Created by iyurenko on 25.11.16.
 */
@Component
public class JmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        getContext().disableJMX();

        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(Thing.class);

        from("jms:thing")
                .unmarshal(format)
                .to("direct:createThing");


    }
}

package com.iyurenko.service;

import com.iyurenko.dao.domain.Thing;

/**
 * Created by iyurenko on 23.11.16.
 */
public interface MessageProcessService {

    void sendDeleteMessage(Thing thing);

    void sendAddMessage(Thing thing);

    String log();
}

package com.moraes

import grails.gorm.services.Service

import java.math.RoundingMode


@Service(Message)
class MessageRepository {
    private static List<Message> messages = Collections.synchronizedList(new ArrayList<>())
    private static Long idCounter = 1L

    List<Message> findAll() {
        messages.sort { -it.createdAt.time }
    }

    Message save(Message message) {
        message.id = idCounter++
        message.createdAt = new Date()
        messages.add(message)
        message
    }

    Long count() {
        messages.size()
    }

    Double getAverageMessageLength() {
        if (messages == null || messages.isEmpty()) {
            return 0.0
        }
        def sum = 0;
        for (msg in messages) {
            sum += msg.content.length()
        }
        new BigDecimal(sum / messages.size()).setScale(4, RoundingMode.HALF_UP).doubleValue()

    }
}
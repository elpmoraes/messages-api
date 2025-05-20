package com.moraes

import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import java.util.Calendar

class MessageRepositorySpec extends Specification implements ServiceUnitTest<MessageRepository> {


    MessageRepository repository = new MessageRepository()

    def setup() {
        repository.@messages.clear()
        repository.@idCounter = 1L
    }

    void "findAll should return messages sorted by createdAt descending"() {
        given:
        def message1 = new Message(content: "Content 1")
        def message2 = new Message(content: "Content 2")


        repository.save(message1)
        sleep(1000)
        repository.save(message2)

        when:
        def result = repository.findAll()

        then:
        result == [message2, message1]
    }

    void "getAverageMessageLength should return average for messages length"() {
        given:
        def message1 = new Message(content: "123")
        def message2 = new Message(content: "12345")
        repository.save(message1)
        repository.save(message2)

        when:
        def result = repository.getAverageMessageLength()

        then:
        result == 4.0000
    }

    void "getAverageMessageLength should return 0 when no messages found"() {


        when:
        def result = repository.getAverageMessageLength()

        then:
        result == 0.0
    }

}

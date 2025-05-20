package com.moraes

import grails.testing.gorm.DomainUnitTest

import spock.lang.Specification

class MessageSpec extends Specification implements DomainUnitTest<Message> {



    void "test author cannot be null"() {
        when:
        domain.author = null

        then:
        !domain.validate(['author'])
        domain.errors['author'].code == 'nullable'
    }

    void "test author cannot be blank"() {
        when:
        domain.author = ""

        then:
        !domain.validate(['author'])
        domain.errors['author'].code == 'blank'
    }

    void "test content cannot be null"() {
        when:
        domain.content = null

        then:
        !domain.validate(['content'])
        domain.errors['content'].code == 'nullable'
    }

    void "test content cannot be blank"() {
        when:
        domain.content = ""

        then:
        !domain.validate(['content'])
        domain.errors['content'].code == 'blank'
    }
}

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

    void "author can contain spaces and letters only"() {
        when:
        domain.author = "Valid Name"

        then:
        domain.validate(['author'])
    }

    void "content must not exceed max size"() {
        when:
        domain.content = "a" * 256

        then:
        !domain.validate(['content'])
        domain.errors['content'].code == 'maxSize.exceeded'
    }


    void "author must not exceed max size"() {
        when:
        domain.author = "a" * 256

        then:
        !domain.validate(['author'])
        domain.errors['author'].code == 'maxSize.exceeded'
    }

}

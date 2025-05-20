package com.moraes

class Message {
    Long id
    String content
    String author
    Date createdAt

    static constraints = {
        content nullable: false, blank: false, maxSize: 255, minSize: 1
        author nullable: false, blank: false, maxSize: 255, minSize: 1, matches: /^[A-Za-z\s]+$/
        createdAt nullable: true
    }

}
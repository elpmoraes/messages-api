package com.moraes
import com.moraes.Message
import grails.converters.JSON


class MessageController {
	static responseFormats = ['json'] //Only json response allowed
    def messageRepository

    def index() {
        try{
            def messages = messageRepository.findAll()
            def response = messages.collect { message ->
                [
                        id: message.id,
                        content: message.content,
                        author: message.author,
                        createdAt: message.createdAt,
                        contentPreview: message.content.length() > 30 ? message.content.substring(0, 30) + "..." : message.content
                ]
            }
            render response as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: "Internal Error: ${e.class.simpleName}"] as JSON)
        }
    }

    def save() {
        try {
            def json = request.JSON
            def message = new Message(
                    content: json.content,
                    author: json.author
            )
            message.validate()
            if (message.hasErrors()) {
                response.status = 400
                def errors = message.errors.allErrors.collect { error ->
                    [field: error.field, message: "Validation Error"]
                }
                render([errors: errors] as JSON)
                return
            }
            message = messageRepository.save(message)

            response.status = 201
            render message as JSON
        } catch (Exception e) {
            response.status = 500
            render([error: "Internal Error: ${e.class.simpleName}"] as JSON)
        }
    }

    def summary() {
        try {
            def response = [
                    totalMessages: messageRepository.count(),
                    averageMessageLength: messageRepository.getAverageMessageLength()
            ]
            render response as JSON

        } catch (Exception e) {
            response.status = 500
            render([error: "Internal Error: ${e.class.simpleName}"] as JSON)
    }
    }
}

package com.moraes

class UrlMappings {
    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/messages"(controller: "message", action: "index", method: "GET")
        "/messages"(controller: "message", action: "save", method: "POST")
        "/messages/summary"(controller: "message", action: "summary", method: "GET")
        "/"(view: "/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}

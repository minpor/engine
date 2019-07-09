package ru.minpor.search.engine.batch.model

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "Person")
data class Person(

        @get:XmlElement(name = "LastName")
        var lastName: String? = null,

        @get:XmlElement(name = "FirstName")
        var firstName: String? = null
)

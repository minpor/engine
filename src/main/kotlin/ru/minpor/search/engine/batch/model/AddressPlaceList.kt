package ru.minpor.search.engine.batch.model

import javax.xml.bind.annotation.XmlElement

data class AddressPlaceList(
        @get:XmlElement(name = "address_place")
        var addressPlace: List<AddressPlace> = mutableListOf())
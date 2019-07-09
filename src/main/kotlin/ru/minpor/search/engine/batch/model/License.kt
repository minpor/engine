package ru.minpor.search.engine.batch.model

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "licenses")
data class License(var name: String? = null,

                   @get:XmlElement(name = "activity_type")
                   var activityType: String? = null,

                   @get:XmlElement(name = "full_name_licensee")
                   var fullNameLicensee: String? = null,

                   @get:XmlElement(name = "brand_name_licensee")
                   var brandNameLicensee: String? = null,

                   var from: String? = null,
                   var address: String? = null,
                   var ogrn: String? = null,
                   var inn: String? = null,

                   @get:XmlElement(name = "work_address_list")
                   var workAddressList: AddressPlaceList? = null,

                   var number: String? = null,
                   var date: String? = null,

                   @get:XmlElement(name = "number_orders")
                   var numberOrders: String? = null,

                   @get:XmlElement(name = "date_order")
                   var dateOrder: String? = null,

                   @get:XmlElement(name = "date_register")
                   var dateRegister: String? = null,

                   @get:XmlElement(name = "number_duplicate")
                   var numberDuplicate: String? = null,

                   @get:XmlElement(name = "date_duplicate")
                   var dateDuplicate: String? = null,
                   var termination: String? = null,

                   @get:XmlElement(name = "date_termination")
                   var dateTermination: String? = null,
                   var information: String? = null,

                   @get:XmlElement(name = "information_regulations")
                   var informationRegulations: String? = null,

                   @get:XmlElement(name = "information_suspension_resumption")
                   var informationSuspensionResumption: String? = null,

                   @get:XmlElement(name = "information_cancellation")
                   var informationCancellation: String? = null,

                   @get:XmlElement(name = "information_reissuing")
                   var informationReissuing: String? = null
)
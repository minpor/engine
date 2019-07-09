package ru.minpor.search.engine.batch.model

data class AddressPlace(var address: String? = null,
                        var index: String? = null,
                        var region: String? = null,
                        var coty: String? = null,
                        var street: String? = null,
                        var works: List<Works> = mutableListOf())
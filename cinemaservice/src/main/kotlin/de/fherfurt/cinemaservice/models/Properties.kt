package de.fherfurt.cinemaservice.models

class Properties(
    private val msg: String,
    private val buildVersion: String,
    private val mailDetails: Map<String, String>,
    private val activeBranches: List<String>
)
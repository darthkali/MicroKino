package de.fherfurt.moviesservice.models

class Properties(
    private val msg: String,
    private val buildVersion: String,
    private val mailDetails: Map<String, String>,
    private val activeBranches: List<String>
)
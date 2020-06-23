package dk.nodes.template.presentation.ui.main

import dk.nodes.nstack.kotlin.models.AppUpdate
import dk.nodes.nstack.kotlin.models.Message
import dk.nodes.nstack.kotlin.models.RateReminder
import dk.nodes.template.models.Employee
import dk.nodes.template.models.SectionsInfo
import dk.nodes.template.presentation.util.SingleEvent
import dk.nodes.template.presentation.util.ViewError

data class MainActivityViewState(
        val errorMessage: SingleEvent<String>? = null,
        val isLoading: Boolean = false,
        val nstackMessage: Message? = null,
        val nstackRateReminder: RateReminder? = null,
        val nstackUpdate: AppUpdate? = null,
        val viewError: SingleEvent<ViewError>? = null,
        val employeesList: ArrayList<Employee>? = null,
        val sectionsList: ArrayList<SectionsInfo>? = null,
        val employee: Employee? = null,
        val message: String? = null


        )
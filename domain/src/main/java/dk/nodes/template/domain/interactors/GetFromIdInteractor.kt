package dk.nodes.template.domain.interactors

import dk.nodes.template.models.Employee
import dk.nodes.template.repositories.EmployeeRepository
import javax.inject.Inject

class GetFromIdInteractor @Inject constructor(private val employesRepository: EmployeeRepository) : BaseAsyncInteractor<ArrayList<Employee>> {
    override suspend fun invoke(): ArrayList<Employee> {
        return employesRepository.getEmployees()

    }
}
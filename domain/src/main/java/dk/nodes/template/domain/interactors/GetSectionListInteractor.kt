package dk.nodes.template.domain.interactors

import dk.nodes.template.models.SectionsInfo
import dk.nodes.template.repositories.EmployeeRepository
import javax.inject.Inject

class GetSectionListInteractor@Inject constructor(private val employesRepository: EmployeeRepository) : BaseAsyncInteractor<ArrayList<SectionsInfo>> {
    override suspend fun invoke(): ArrayList<SectionsInfo> {
        return employesRepository.getSectionNames()

    }

}
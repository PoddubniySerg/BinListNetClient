package go.cft.domain.usecases

import go.cft.domain.exceptions.GetBinsListException
import go.cft.domain.models.results.GetBinsListResult
import go.cft.domain.repositories.BinListRepository
import go.cft.domain.util.Sorter
import javax.inject.Inject

class GetBinsListUseCase @Inject constructor() {

    @Inject
    lateinit var repository: BinListRepository

    @Inject
    lateinit var sorter: Sorter

    suspend fun execute(): GetBinsListResult {
        try {
            val result = sorter.sort(repository.getBinsList())
            return GetBinsListResult(result)
        } catch (ex: Exception) {
            throw GetBinsListException("Get bins data error")
        }
    }
}
package go.cft.domain.usecases

import go.cft.domain.exceptions.GetBinsListException
import go.cft.domain.models.results.BinsList
import go.cft.domain.repositories.BinListRepository
import go.cft.domain.util.Converter
import javax.inject.Inject

class GetBinsListUseCase @Inject constructor() {

    @Inject
    lateinit var repository: BinListRepository

    @Inject
    lateinit var converter: Converter

    suspend fun execute(): BinsList {
        try {
            val result = repository.getBinsList().sortedBy { it.id }
            return BinsList(result.map { converter.convert(it) })
        } catch (ex: Exception) {
            throw GetBinsListException("Get bins data error")
        }
    }
}
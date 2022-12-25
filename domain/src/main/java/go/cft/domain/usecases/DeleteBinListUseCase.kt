package go.cft.domain.usecases

import go.cft.domain.exceptions.DeleteBinListException
import go.cft.domain.models.params.DeleteBinListParam
import go.cft.domain.models.params.RemoveBinListByBinParam
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

class DeleteBinListUseCase @Inject constructor() {

    @Inject
    lateinit var repository: BinListRepository

    suspend fun execute(param: DeleteBinListParam) {
        try {
            repository.removeBinList(RemoveBinListByBinParam(param.bin))
        } catch (ex: Exception) {
            throw DeleteBinListException("Error remove element")
        }
    }
}
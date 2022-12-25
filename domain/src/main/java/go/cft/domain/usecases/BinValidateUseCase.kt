package go.cft.domain.usecases

import go.cft.domain.models.params.BinValidateParam
import go.cft.domain.models.results.BinIsValid
import javax.inject.Inject

class BinValidateUseCase @Inject constructor() {

    companion object {
        const val MAX_SIZE_BIN = 8
        const val MIN_SIZE_BIN = 6
    }

    private val regex = "^[0-9]+\$".toRegex()

    fun execute(param: BinValidateParam): BinIsValid {
        return try {
            val bin = param.bin
            var binIsValid = false
            bin?.let {
                binIsValid =
                    bin.isEmpty() ||
                            bin.length in MIN_SIZE_BIN..MAX_SIZE_BIN &&
                            regex.matches(bin)
            }
            BinIsValid(binIsValid)
        } catch (e: Exception) {
            BinIsValid(result = false)
        }
    }
}
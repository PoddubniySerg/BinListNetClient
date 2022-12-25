package go.cft.domain.usecases

import go.cft.domain.models.params.GetUriStringPhoneParam
import go.cft.domain.models.results.UriStringPhone
import javax.inject.Inject

class GetUriStringPhoneUseCase @Inject constructor() {

    fun execute(param: GetUriStringPhoneParam): UriStringPhone {
        return UriStringPhone("tel:${param.phone.trim()}")
    }
}
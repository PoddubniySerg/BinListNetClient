package go.cft.domain.usecases

import go.cft.domain.models.params.GetUriStringUrlParam
import go.cft.domain.models.results.UriStringUrl
import javax.inject.Inject

class GetUriStringUrlUseCase @Inject constructor() {

    fun execute(param: GetUriStringUrlParam): UriStringUrl {
        return UriStringUrl("http://${param.url.trim()}")
    }
}
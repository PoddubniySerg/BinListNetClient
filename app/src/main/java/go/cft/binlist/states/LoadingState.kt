package go.cft.binlist.states

sealed class LoadingState {

    object IsLoading : LoadingState()
    object Loaded : LoadingState()
}
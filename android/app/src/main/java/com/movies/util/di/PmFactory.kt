package md.medici.util.di.pm

import me.dmdev.rxpm.PresentationModel
import javax.inject.Inject
import javax.inject.Provider

interface PmFactory {
    fun <T : PresentationModel> create(pmClass: Class<T>): T
}

class PmFactoryImpl @Inject constructor(
    private val creators: Map<Class<out PresentationModel>, @JvmSuppressWildcards Provider<PresentationModel>>
) : PmFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : PresentationModel> create(pmClass: Class<T>): T {
        var creator: Provider<PresentationModel>? = creators[pmClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (pmClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) throw IllegalArgumentException("Dependency not provided: $pmClass")
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
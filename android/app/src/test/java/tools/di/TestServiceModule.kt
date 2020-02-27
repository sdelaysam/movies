package tools.di

import com.movies.data.service.ApiService
import dagger.Module
import dagger.Provides
import tools.data.service.TestApiService
import javax.inject.Singleton

@Module
class TestServiceModule {

    @Provides
    @Singleton
    fun provideTestApiService() = TestApiService()

    @Provides
    fun provideApiService(service: TestApiService): ApiService = service
}
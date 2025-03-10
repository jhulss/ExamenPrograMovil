package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.GithubRepository
import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.framework.github.GithubRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.usecases.FindGitAlias
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerRetrofitBuilder(@ApplicationContext context: Context) : RetrofitBuilder {
        return RetrofitBuilder(context)
    }


    @Provides
    @Singleton
    fun gitRemoteDataSource(retrofiService: RetrofitBuilder): IGitRemoteDataSource {
        return GithubRemoteDataSource(retrofiService)
    }

    @Provides
    @Singleton
    fun gitRepository(remoteDataSource: IGitRemoteDataSource): GithubRepository {
        return GithubRepository(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGitUseCases(githubRepository: GithubRepository): FindGitAlias {
        return FindGitAlias(githubRepository)
    }
}
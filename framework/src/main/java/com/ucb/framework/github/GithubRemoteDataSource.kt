package com.ucb.framework.github

import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.domain.Gitalias
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitBuilder

class GithubRemoteDataSource(
    val retrofiService: RetrofitBuilder
) : IGitRemoteDataSource {

    override suspend fun fetch(userID: String): Gitalias {
        return retrofiService.apiService.getInfoAvatar(userID).toModel()
    }
}

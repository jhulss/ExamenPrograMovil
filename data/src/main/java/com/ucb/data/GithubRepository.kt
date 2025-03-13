package com.ucb.data

import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.domain.Gitalias

class GithubRepository (private val remoteDataSource: IGitRemoteDataSource){

    suspend fun findbyId(userId: String): NetworkResult<Gitalias> {
       return this.remoteDataSource.fetch(userId)
    }
}
package com.ucb.data

import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.domain.Gitalias

class GithubRepository (val remoteDataSource: IGitRemoteDataSource){

    fun findbyId(userId: String): Gitalias {
       return this.remoteDataSource.fetch(userId)
    }
}
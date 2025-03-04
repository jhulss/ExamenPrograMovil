package com.ucb.data.git

import com.ucb.domain.Gitalias

interface IGitRemoteDataSource {
    fun fetch(userID: String): Gitalias
}
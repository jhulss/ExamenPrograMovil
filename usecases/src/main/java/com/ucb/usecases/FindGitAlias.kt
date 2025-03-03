package com.ucb.usecases

import com.ucb.domain.Gitalias
import kotlinx.coroutines.delay

class FindGitAlias {
    suspend fun invoke(userId: String) : Gitalias {
        delay(100)
        return Gitalias("calyr", "https://avatars.githubusercontent.com/u/874321?v=4")
    }
}
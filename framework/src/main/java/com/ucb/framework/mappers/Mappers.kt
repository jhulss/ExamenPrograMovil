package com.ucb.framework.mappers

import com.ucb.domain.Gitalias
import com.ucb.framework.dto.AvatarResponseDto
import com.ucb.framework.persistence.GitAccount

fun AvatarResponseDto.toModel(): Gitalias {
    return Gitalias(
        login = login,
        avatarUrl = url
    )
}

fun Gitalias.toEntity(): GitAccount {
    return GitAccount(login)
}

fun GitAccount.toModel(): Gitalias {
    return Gitalias(
        alias,
        ""
    )
}
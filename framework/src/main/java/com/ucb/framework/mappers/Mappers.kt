package com.ucb.framework.mappers

import com.ucb.domain.Gitalias
import com.ucb.framework.dto.AvatarResponseDto

fun AvatarResponseDto.toModel(): Gitalias {
    return return Gitalias(
        login = login,
        avatarUrl = url
    )
}
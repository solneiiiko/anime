/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.impl.di

import shum.oks.lab.core.di.BaseComponentHolder

object EntityAnimeDomainImplComponentHolder : BaseComponentHolder<
    EntityAnimeDomainImplApi,
    EntityAnimeDomainImplDependencies
>() {

    override fun buildComponent(dependencies: EntityAnimeDomainImplDependencies): EntityAnimeDomainImplApi =
        DaggerEntityAnimeDomainImplComponent.builder()
            .entityAnimeDomainImplDependencies(dependencies)
            .build()
}

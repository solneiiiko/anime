/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.di

import dagger.Component


@Component(
    dependencies = [
        CatalogUiDependencies::class
    ]
)
internal abstract class CatalogUiComponent : CatalogUiApi()

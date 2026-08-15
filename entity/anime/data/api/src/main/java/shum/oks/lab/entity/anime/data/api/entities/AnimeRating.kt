/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.entities


enum class AnimeRating(
    val value: String,
) {
    G("g"),
    PG("pg"),
    PG_13("pg13"),
    R("r17"),
    R_PLUS("r"),
    RX("rx"),
    ;
}

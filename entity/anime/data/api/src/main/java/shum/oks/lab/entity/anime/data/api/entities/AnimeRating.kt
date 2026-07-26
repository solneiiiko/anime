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
    G("G - All Ages"),
    PG("PG - Children"),
    PG_13("PG-13 - Teens 13 or older"),
    R("R - 17+ (violence & profanity)"),
    R_PLUS("R+ - Mild Nudity"),
    RX("Rx - Hentai"),
    ;
}

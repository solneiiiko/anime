/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class AnimeRatingResponse {
    @SerialName("G - All Ages")
    G,

    @SerialName("PG - Children")
    PG,

    @SerialName("PG-13 - Teens 13 or older")
    PG_13,

    @SerialName("R - 17+ (violence & profanity)")
    R,

    @SerialName("R+ - Mild Nudity")
    R_PLUS,

    @SerialName("Rx - Hentai")
    RX,
    ;
}

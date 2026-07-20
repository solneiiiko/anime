/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.nullable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object AnimeTypeResponseSerializer : KSerializer<AnimeTypeResponse?> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            serialName = "AnimeTypeResponse",
            kind = PrimitiveKind.STRING
        ).nullable

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): AnimeTypeResponse? {
        if (!decoder.decodeNotNullMark()) return decoder.decodeNull()
        val value = decoder.decodeString()
        return AnimeTypeResponse.entries.firstOrNull {
            getLowercaseSerialName(it).equals(value, ignoreCase = true)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: AnimeTypeResponse?) {
        if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeNotNullMark()
            encoder.encodeString(getLowercaseSerialName(value))
        }
    }

    private fun getLowercaseSerialName(entry: AnimeTypeResponse): String {
        return when (entry) {
            AnimeTypeResponse.TV -> "tv"
            AnimeTypeResponse.OVA -> "ova"
            AnimeTypeResponse.MOVIE -> "movie"
            AnimeTypeResponse.SPECIAL -> "special"
            AnimeTypeResponse.ONA -> "ona"
            AnimeTypeResponse.MUSIC -> "music"
            AnimeTypeResponse.CM -> "cm"
            AnimeTypeResponse.PV -> "pv"
            AnimeTypeResponse.TV_SPECIAL -> "tv special"
        }
    }
}

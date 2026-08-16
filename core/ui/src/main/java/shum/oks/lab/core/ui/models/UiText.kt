/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.models

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

sealed interface UiText {
    data class Plain(val value: String) : UiText

    /**
     * A string loaded from a string resource.
     *
     * This is a demo implementation.
     * The use of `Any` for formatting arguments is a deliberate simplification.
     *
     * Wondering how I'd implement it in production? Ask me. ^_^__/
     */
    data class StringResource(
        @StringRes val resId: Int,
        val args: ImmutableList<Any> = emptyList<Any>().toImmutableList()
    ) : UiText {
        constructor(@StringRes resId: Int, vararg args: Any) : this(resId, args.toImmutableList())
    }

    data class PluralStringResource(
        @PluralsRes val resId: Int,
        val quantity: Int,
        val args: ImmutableList<Any> = emptyList<Any>().toImmutableList()
    ) : UiText {
        constructor(@PluralsRes resId: Int, quantity: Int, vararg args: Any) :
                this(resId, quantity, args.toImmutableList())
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is Plain -> value
            is StringResource -> stringResource(resId, *args.toTypedArray())
            is PluralStringResource -> pluralStringResource(resId, quantity, *args.toTypedArray())
        }
    }
}

@Composable
fun Iterable<UiText>.joinToString(
    separator: String,
): String = buildString {
    this@joinToString.forEachIndexed { index, item ->
        if (index > 0) append(separator)
        append(item.asString())
    }
}

package com.example.expensesage.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormatSymbols

/**
 * This class is responsible for the decimal formatter.
 *
 * @constructor
 * Constructs a DecimalFormatter.
 *
 * @param symbols The decimal format symbols
 */
class DecimalFormatter(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {

    private val thousandsSeparator = symbols.groupingSeparator
    private val decimalSeparator = symbols.decimalSeparator

    /**
     * This function formats the input.
     *
     * @param current The current input
     * @param input The input to format
     * @return The formatted input
     */
    fun cleanup(current: String, input: String): String {

        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"
        if (input.matches("^0[1-9]".toRegex())) return input.substring(1)

        if (current == "0")
            return input

        val sb = StringBuilder()

        var hasDecimalSep = false

        var decimals = 0

        for (char in input) {
            if (char.isDigit()) {
                if (hasDecimalSep) {
                    decimals++
                    if (decimals > 2) break
                    sb.append(char)
                } else {
                    sb.append(char)
                }

                continue
            } else if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true

            }

        }

        return sb.toString()
    }

    /**
     * This function formats the input for visual.
     *
     * @param input The input to format
     * @return The formatted input
     */
    fun formatForVisual(input: String): String {

        val split = input.split(decimalSeparator)

        val intPart =
            split[0].reversed().chunked(3).joinToString(separator = thousandsSeparator.toString())
                .reversed()

        val fractionPart = split.getOrNull(1)

        return if (fractionPart == null) intPart else intPart + decimalSeparator + fractionPart.take(
            2
        )
    }
}

/**
 * This class is responsible for the textfield visual transformation.
 *
 * @property decimalFormatter The decimal formatter
 */
class CurrencyVisualTransformation(
    private val decimalFormatter: DecimalFormatter
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val inputText = text.text
        val formattedNumber = decimalFormatter.formatForVisual(inputText)

        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles
        )

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = inputText.length, formattedContentLength = formattedNumber.length
        )

        return TransformedText(newText, offsetMapping)
    }
}

/**
 * This class is responsible for the offset mapping.
 *
 * @property contentLength The content length
 * @property formattedContentLength The formatted content length
 */
private class FixedCursorOffsetMapping(
    private val contentLength: Int,
    private val formattedContentLength: Int,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = formattedContentLength
    override fun transformedToOriginal(offset: Int): Int = contentLength
}


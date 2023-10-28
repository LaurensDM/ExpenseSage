package com.example.expensesage.ui.utils

import android.icu.text.DecimalFormat
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.lang.Integer.max

/**
 * Visual transformation that masks the input value as a currency value.
 *
 * @property fixedCursorAtTheEnd If true, the cursor will be fixed at the end of the text. If false, the cursor will be movable.
 * @property numberOfDecimals The number of decimals that the currency value should have.
 */
class CurrencyVisualTransformation(
    private val fixedCursorAtTheEnd: Boolean = true,
    private val numberOfDecimals: Int = 2,
) : VisualTransformation {
    /**
     * Function that is called when the user inputs a value in the text field. Masks the input value as a currency value.
     *
     * @param text The text that is inputted by the user
     * @return The transformed text
     */
    override fun filter(text: AnnotatedString): TransformedText {
//        val thousandsSeparator = symbols.groupingSeparator
//        val decimalSeparator = symbols.decimalSeparator
//        val zero = symbols.zeroDigit

        val numberFormat = DecimalFormat("#,##0.00")
        val inputText = text.text

        val numericInput = inputText.filter { it.isDigit() || it == '.' }

        val numericValue = numericInput.toDoubleOrNull() ?: 0.0

        val formattedNumber = numberFormat.format(numericValue)

        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles,
        )

        val offsetMapping = if (fixedCursorAtTheEnd) {
            FixedCursorOffsetMapping(
                contentLength = inputText.length,
                formattedContentLength = formattedNumber.length,
            )
        } else {
            MovableCursorOffsetMapping(
                unmaskedText = text.toString(),
                maskedText = newText.toString(),
                decimalDigits = numberOfDecimals,
            )
        }

        return TransformedText(newText, offsetMapping)
    }

    /**
     * Offset mapping class that is used to map the cursor position from the original text to the transformed text.
     *
     * @property contentLength The length of the original text
     * @property formattedContentLength The length of the transformed text
     */
    private class FixedCursorOffsetMapping(
        private val contentLength: Int,
        private val formattedContentLength: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = formattedContentLength
        override fun transformedToOriginal(offset: Int): Int = contentLength
    }

    /**
     * Offset mapping class that is used to map the cursor position from the original text to the transformed text. This class is used when the cursor is not fixed at the end.
     *
     * @property unmaskedText The unmasked text
     * @property maskedText The masked text
     * @property decimalDigits The number of decimal digits
     */
    private class MovableCursorOffsetMapping(
        private val unmaskedText: String,
        private val maskedText: String,
        private val decimalDigits: Int,
    ) : OffsetMapping {
        /**
         * Function that is called when the user inputs a value in the text field. Masks the input value as a currency value.
         *
         * @param offset The offset of the cursor
         * @return The transformed offset
         */
        override fun originalToTransformed(offset: Int): Int = when {
            unmaskedText.length <= decimalDigits -> {
                maskedText.length - (unmaskedText.length - offset)
            }

            else -> {
                offset + offsetMaskCount(offset, maskedText)
            }
        }

        /**
         * Function that is called when the user inputs a value in the text field. Masks the input value as a currency value.
         *
         * @param offset The offset of the cursor
         * @return The transformed offset
         */
        override fun transformedToOriginal(offset: Int): Int = when {
            unmaskedText.length <= decimalDigits -> {
                max(unmaskedText.length - (maskedText.length - offset), 0)
            }

            else -> {
                offset - maskedText.take(offset).count { !it.isDigit() }
            }
        }

        /**
         * This function is used to offset the mask count.
         *
         * @param offset The offset of the cursor
         * @param maskedText    The masked text
         * @return The offset mask count
         */
        private fun offsetMaskCount(offset: Int, maskedText: String): Int {
            var maskOffsetCount = 0
            var dataCount = 0
            for (maskChar in maskedText) {
                if (!maskChar.isDigit()) {
                    maskOffsetCount++
                } else if (++dataCount > offset) {
                    break
                }
            }
            return maskOffsetCount
        }
    }
}

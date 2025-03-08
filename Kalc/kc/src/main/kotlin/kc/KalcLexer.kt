package rrao.kalc.kc

enum class KalcTokenType {
    NUMBER,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    LPAREN,
    RPAREN,
    IDENTIFIER,
    FN,
    COLON,
    SEMICOLON,
    COMMA,
    LBRACE, // {
    RBRACE, // }
    RETURN, // return
    LET, // let
    EOF,
    ERROR
}

data class KalcToken(val type: KalcTokenType, val value: String) {
    companion object {

        fun createToken(aChar: Char, operation: () -> Unit): KalcToken {
            when (aChar) {
                '+' -> {
                    return KalcToken(KalcTokenType.PLUS, aChar.toString()).also { operation() }
                }
                '-' -> {
                    return KalcToken(KalcTokenType.MINUS, aChar.toString()).also { operation() }
                }
                '*' -> {
                    return KalcToken(KalcTokenType.STAR, aChar.toString()).also { operation() }
                }
                '/' -> {
                    return KalcToken(KalcTokenType.SLASH, aChar.toString()).also { operation() }
                }
                '(' -> {
                    return KalcToken(KalcTokenType.LPAREN, aChar.toString()).also { operation() }
                }
                ')' -> {
                    return KalcToken(KalcTokenType.RPAREN, aChar.toString()).also { operation() }
                }
                '{' -> {
                    return KalcToken(KalcTokenType.LBRACE, aChar.toString()).also { operation() }
                }
                '}' -> {
                    return KalcToken(KalcTokenType.RBRACE, aChar.toString()).also { operation() }
                }
                ':' -> {
                    operation()
                    return KalcToken(KalcTokenType.COLON, aChar.toString()).also { operation() }
                }
                ';' -> {
                    operation()
                    return KalcToken(KalcTokenType.SEMICOLON, aChar.toString()).also { operation() }
                }
                ',' -> {
                    operation()
                    return KalcToken(KalcTokenType.COMMA, aChar.toString()).also { operation() }
                }
                else -> {
                    return KalcToken(KalcTokenType.ERROR, aChar.toString()).also { operation() }
                }
            }
        }
    }
}

class KalcLexer(private val source: String) {

    private var position: Int = 0
    private var currentChar: Char? = source.getOrNull(position)

    fun nextToken(): KalcToken {
        while(currentChar != null) {
            if (currentChar!!.isWhitespace()) { skipWhitespace();continue;}
            if (currentChar!!.isDigit()) return parseNumber()
            if (currentChar!!.isLetter()) return parseIdentifierOrKeyword()
            return KalcToken.createToken(currentChar!!, operation = { advance() })
        }
        return KalcToken(KalcTokenType.EOF, "")
    }


    private fun skipWhitespace() {
        while (currentChar != null && currentChar!!.isWhitespace()) {
            advance()
        }
    }

    private fun advance(): Unit {
        position++
        currentChar = source.getOrNull(position)
    }

    private fun parseNumber(): KalcToken {
        val builder = StringBuilder()
        while (currentChar != null && currentChar!!.isDigit()) {
            builder.append(currentChar)
            advance()
        }
        return KalcToken(KalcTokenType.NUMBER, builder.toString())
    }

    private fun parseIdentifierOrKeyword(): KalcToken {
        val builder = StringBuilder()
        while (currentChar != null && currentChar!!.isLetter()) {
            builder.append(currentChar)
            advance()
        }
        val identifier = builder.toString()
        return when (identifier) {
            "fn" -> KalcToken(KalcTokenType.FN, "fn")
            "return" -> KalcToken(KalcTokenType.RETURN, "return")
            "let" -> KalcToken(KalcTokenType.LET, "let")
            else -> KalcToken(KalcTokenType.IDENTIFIER, identifier)
        }
    }

}
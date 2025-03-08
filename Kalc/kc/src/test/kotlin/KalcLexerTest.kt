package rrao.kalc.kc.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import rrao.kalc.kc.KalcLexer
import rrao.kalc.kc.KalcToken
import rrao.kalc.kc.KalcTokenType
class KalcLexerTest {

    @Test
    fun testSimpleExpression() {
        val lexer = KalcLexer("1 + 2 * 3")
        val tokens = generateKalcTokens(lexer)

        val expectedKalcTokens = listOf(
            KalcToken(KalcTokenType.NUMBER, "1"),
            KalcToken(KalcTokenType.PLUS, "+"),
            KalcToken(KalcTokenType.NUMBER, "2"),
            KalcToken(KalcTokenType.STAR, "*"),
            KalcToken(KalcTokenType.NUMBER, "3"),
            KalcToken(KalcTokenType.EOF, "")
        )

        assertEquals(expectedKalcTokens, tokens)
    }

    @Test
    fun testFunctionDefinition() {
        val lexer = KalcLexer("fn add(x, y) { return x + y; }")
        val tokens = generateKalcTokens(lexer)

        val expectedKalcTokens = listOf(
            KalcToken(KalcTokenType.FN, "fn"),
            KalcToken(KalcTokenType.IDENTIFIER, "add"),
            KalcToken(KalcTokenType.LPAREN, "("),
            KalcToken(KalcTokenType.IDENTIFIER, "x"),
            KalcToken(KalcTokenType.COMMA, ","),
            KalcToken(KalcTokenType.IDENTIFIER, "y"),
            KalcToken(KalcTokenType.RPAREN, ")"),
            KalcToken(KalcTokenType.LBRACE, "{"),
            KalcToken(KalcTokenType.RETURN, "return"),
            KalcToken(KalcTokenType.IDENTIFIER, "x"),
            KalcToken(KalcTokenType.PLUS, "+"),
            KalcToken(KalcTokenType.IDENTIFIER, "y"),
            KalcToken(KalcTokenType.SEMICOLON, ";"),
            KalcToken(KalcTokenType.RBRACE, "}"),
            KalcToken(KalcTokenType.EOF, "")
        )

        assertEquals(expectedKalcTokens, tokens)
    }

    @Test
    fun testComplexExpression() {
        val lexer = KalcLexer("10 + (2 * (3 - 1)) / 4")
        val tokens = generateKalcTokens(lexer)

        val expectedKalcTokens = listOf(
            KalcToken(KalcTokenType.NUMBER, "10"),
            KalcToken(KalcTokenType.PLUS, "+"),
            KalcToken(KalcTokenType.LPAREN, "("),
            KalcToken(KalcTokenType.NUMBER, "2"),
            KalcToken(KalcTokenType.STAR, "*"),
            KalcToken(KalcTokenType.LPAREN, "("),
            KalcToken(KalcTokenType.NUMBER, "3"),
            KalcToken(KalcTokenType.MINUS, "-"),
            KalcToken(KalcTokenType.NUMBER, "1"),
            KalcToken(KalcTokenType.RPAREN, ")"),
            KalcToken(KalcTokenType.RPAREN, ")"),
            KalcToken(KalcTokenType.SLASH, "/"),
            KalcToken(KalcTokenType.NUMBER, "4"),
            KalcToken(KalcTokenType.EOF, "")
        )

        assertEquals(expectedKalcTokens, tokens)
    }

    @Test
    fun testInvalidCharacters() {
        val lexer = KalcLexer("1 + 2 @ 3")
        val tokens = generateKalcTokens(lexer)

        val expectedKalcTokens = listOf(
            KalcToken(KalcTokenType.NUMBER, "1"),
            KalcToken(KalcTokenType.PLUS, "+"),
            KalcToken(KalcTokenType.NUMBER, "2"),
            KalcToken(KalcTokenType.ERROR, "@"),
            KalcToken(KalcTokenType.NUMBER, "3"),
            KalcToken(KalcTokenType.EOF, "")
        )

        assertEquals(expectedKalcTokens, tokens)
    }

    // Add more test cases as needed

    private fun generateKalcTokens(lexer: KalcLexer): List<KalcToken> {
        val tokens = mutableListOf<KalcToken>()
        while (true) {
            val token = lexer.nextToken()
            tokens.add(token)
            if (token.type == KalcTokenType.EOF) {
                break
            }
        }
        return tokens
    }
}
SCRIPT_START
{
    LVAR_INT scplayer numero
    LVAR_FLOAT cordx cordy cordz

    GET_PLAYER_CHAR 0 scplayer

    PRINT_FORMATTED_NOW "Hello World Working" 5000

    WHILE TRUE
        WAIT 0

        // Mostrar cordenadas ao pressionar TAB
        WHILE IS_KEY_PRESSED VK_TAB
            WAIT 0

            GET_CHAR_COORDINATES scplayer (cordx) (cordy) (cordz)
            PRINT_FORMATTED_NOW "X: %.3f Y: %.3f Z: %.3f" 0001 (cordx) (cordy) (cordz)
        ENDWHILE

        // Mostrar um numero na tela
        WHILE IS_KEY_PRESSED VK_KEY_T
            WAIT 0

            IF IS_KEY_PRESSED VK_KEY_U
                numero += 1
                WHILE IS_KEY_PRESSED VK_KEY_U
                    WAIT 0
                ENDWHILE
            ENDIF

            IF IS_KEY_PRESSED VK_KEY_J
                numero -= 1
                WHILE IS_KEY_PRESSED VK_KEY_J
                    WAIT 0
                ENDWHILE
            ENDIF

            PRINT_FORMATTED_NOW "%i" 0001 (numero)
        ENDWHILE
    ENDWHILE
}
SCRIPT_END
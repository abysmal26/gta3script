// Copyright RitzyDevelopment Global Group LLC 2021

SCRIPT_START
{
    // VARIAVEIS \\
    LVAR_INT scplayer numero tdm
    LVAR_FLOAT x y z

    // SCPLAYER \\

    GET_PLAYER_CHAR 0 scplayer

    // MENSAGEM DE INICIO \\

    PRINT_FORMATTED_NOW "Hello World Working" 5000

    // LE O .INI \\

    // Le o arquivo 'Config.ini'
    READ_INT_FROM_INI_FILE "modloader\My Scripts\Config.ini" "config" "tdm" tdm

    // Verifica se o arquivo foi lido
    IF NOT READ_INT_FROM_INI_FILE "modloader\My Scripts\Config.ini" "config" "tdm" tdm
        PRINT_FORMATTED_NOW "Não fou possivel ler 'tdm' no arquivo 'modloader\My Scripts\Config.ini'" 7000
    ENDIF

    // ENQUANTO SIM \\
    WHILE TRUE
        WAIT 0

        // Mostrar cordenadas ao pressionar TAB
        IF tdm = 1 // Verifica se a variavel 'tdm' é igual a 1
            WAIT 0

            WHILE IS_KEY_PRESSED VK_TAB
                WAIT 0

                GET_CHAR_COORDINATES scplayer (x y z)
                PRINT_FORMATTED_NOW "X: %.3f Y: %.3f Z: %.3f" 0001 (x y z)
            ENDWHILE
        ENDIF

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

        // Regenerar vida
        IF TEST_CHEAT VIDA
            SET_CHAR_HEALTH scplayer 5000
            PRINT_FORMATTED_NOW "Vida regenerada!" 1000
        ENDIF

        // Noclip
        IF tdm = 2 // Verifica se a variavel 'tdm' é igual a 2
            WHILE IS_KEY_PRESSED VK_TAB
                WAIT 0

                WHILE IS_KEY_PRESSED VK_KEY_W
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    y += 1.0
                    z -= 1.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE

                WHILE IS_KEY_PRESSED VK_KEY_S
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    y -= 1.0
                    z -= 1.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE

                WHILE IS_KEY_PRESSED VK_KEY_D
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    x += 1.0
                    z -= 1.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE

                WHILE IS_KEY_PRESSED VK_KEY_A
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    x -= 1.0
                    z -= 1.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE

                WHILE IS_KEY_PRESSED VK_KEY_E
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    z += 1.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE

                WHILE IS_KEY_PRESSED VK_KEY_Q
                    WAIT 0

                    GET_CHAR_COORDINATES scplayer (x y z)
                    z -= 2.0
                    SET_CHAR_COORDINATES scplayer (x y z)
                ENDWHILE
            ENDWHILE
        ENDIF
    ENDWHILE
}
SCRIPT_END

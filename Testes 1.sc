// Copyright RitzyDevelopment Global Group LLC 2021

SCRIPT_START
{
    LVAR_INT scplayer numero tdm
    LVAR_FLOAT x y z

    GET_PLAYER_CHAR 0 scplayer

    PRINT_FORMATTED_NOW "Hello World Working" 5000

    // Le a variavel 'tdm'
    // Eu não dei porque dei o nome de 'tdm' para variavel
    READ_INT_FROM_INI_FILE "Config.ini" "config" "tdm" tdm
    // Se o script não conseguir ler a variavel 'tdm' ele para por 16 minutos
    IF NOT READ_INT_FROM_INI_FILE "Config.ini" "config" "tdm" tdm
        PRINT_FORMATTED_NOW "Não foi possivel ler 'tdm' no arquivo 'Config.ini'" 7000
        WAIT 7000
        PRINT_FORMATTED_NOW "Desligando o script" 7000
        WAIT 960000
    ENDIF

    // Loop
    WHILE TRUE
        WAIT 0

        // Mostrar cordenadas ao pressionar TAB (se a variavel 'tdm' for igual a 1)
        IF tdm = 1 
            WAIT 0

            WHILE IS_KEY_PRESSED VK_TAB
                WAIT 0

                GET_CHAR_COORDINATES scplayer (x y z)
                PRINT_FORMATTED_NOW "X: %.3f Y: %.3f Z: %.3f" 0001 (x y z)
            ENDWHILE
        ENDIF

        // Aumenta e abaixa o numero segurando T e pressionando U ou J
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

        // Cheat de vida (digitando VIDA)
        IF TEST_CHEAT VIDA
            SET_CHAR_HEALTH scplayer 5000
            PRINT_FORMATTED_NOW "Vida regenerada!" 1000
        ENDIF

        // AirBrake ou NoClip (tanto faz) segurando TAB (se a variavel 'tdm' for igual a 2)
        // Olha o codigo para ver os comandos direito
        IF tdm = 2
            IF IS_KEY_PRESSED VK_TAB
                WAIT 0

                CLEO_CALL airbraketp 0 (scplayer)
            ENDIF
        ENDIF

        // Mostra a rua mais proxima ao pressionar END
        IF IS_KEY_PRESSED VK_END
            CLEO_CALL get_closest_road 0 (scplayer) (x y z)
            PRINT_FORMATTED_NOW "A coord da rua mais proxima eh: %.3f %.3f %.3f" 1 (x y z)
            DRAW_CORONA (x y z) (1.0) (CORONATYPE_SHINYSTAR, FLARETYPE_NONE) (255 0 0)
        ENDIF

        // TP (apenas)
        IF TEST_CHEAT TP
            GET_CHAR_COORDINATES scplayer (x y z)
            PRINT_FORMATTED_NOW "Script ativado" 1000
            WAIT 5000
            SET_CHAR_COORDINATES scplayer (x y z)
        ENDIF
    ENDWHILE
}
SCRIPT_END

// Acha a rua mais proxima
{
    LVAR_INT char // In

    LVAR_FLOAT char_x char_y char_z
    LVAR_FLOAT node_x node_y node_z

    get_closest_road:
    GET_CHAR_COORDINATES char (char_x char_y char_z)
    GET_CLOSEST_CAR_NODE (char_x char_y char_z) (node_x node_y node_z)
    CLEO_RETURN 0 (node_x node_y node_z)
}

// AirBrake TP
{
    LVAR_INT scplayer
    LVAR_FLOAT x y z

    airbraketp:
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

    CLEO_RETURN 0
}
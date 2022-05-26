// Abysmal - abysmal26.github.io - ayo.so/abysmal26
// HyPeRiS Group

SCRIPT_START
{
    LVAR_INT scplayer numero tdm
    LVAR_FLOAT x y z

    GET_PLAYER_CHAR 0 scplayer

    PRINT_FORMATTED_NOW "Testes 1 funcionando" 5000

    // Le a variavel 'tdm'
    READ_INT_FROM_INI_FILE "config.ini" "config" "tdm" tdm
    // Se o script não conseguir ler a variavel 'tdm' ele para
    IF NOT READ_INT_FROM_INI_FILE "Config.ini" "config" "tdm" tdm
        PRINT_FORMATTED_NOW "Não foi possivel ler 'tdm' no arquivo 'config.ini'" 7000
        WAIT 7000
        PRINT_FORMATTED_NOW "Parando o script" 7000
        TERMINATE_THIS_CUSTOM_SCRIPT
    ENDIF

    // Loop
    WHILE TRUE
        WAIT 0

        //! Só funciona se a varialvel 'tdm' for igual a 1
        //* Mostra as coordenadas
        // Só segurar TAB
        IF tdm = 1 
            WAIT 0

            WHILE IS_KEY_PRESSED VK_TAB
                WAIT 0

                GET_CHAR_COORDINATES scplayer (x y z)
                PRINT_FORMATTED_NOW "X: %.3f Y: %.3f Z: %.3f" 0001 (x y z)
            ENDWHILE
        ENDIF

        //* Aumenta e abaixa o numero
        /*
        ↓↓↓ Segurando T ↓↓↓
        Pressionar U aumenta
        Pressionar J abaixa
         */
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

        //* Cheat de vida
        // Só digitar VIDA em qualquer momento do jogo
        IF TEST_CHEAT VIDA
            SET_CHAR_HEALTH scplayer 5000
            PRINT_FORMATTED_NOW "Vida regenerada!" 1000
        ENDIF

        //! Só funciona se a variavel 'tdm' for igual a 2
        //* AirBrake/NoClip
        /*
        ↓↓↓ Segurando TAB ↓↓↓
        W vai para frente e S para tras
        A vai para a esquerda e D para direita
        E vai para a cima e Q para baixo
        */
        IF tdm = 2
            IF IS_KEY_PRESSED VK_TAB
                WAIT 0

                CLEO_CALL airbraketp 0 (scplayer)
            ENDIF
        ENDIF

        //* Mostra a rua mais proxima
        // Só pressionar END
        IF IS_KEY_PRESSED VK_END
            CLEO_CALL get_closest_road 0 (scplayer) (x y z)
            PRINT_FORMATTED_NOW "A coord da rua mais proxima eh: %.3f %.3f %.3f" 1 (x y z)
            DRAW_CORONA (x y z) (1.0) (CORONATYPE_SHINYSTAR, FLARETYPE_NONE) (255 0 0)
        ENDIF

        //* TimeBrake
        // Volta onde estava 5 segundos atras
        // Só digitar TB em qualquer momento do jogo
        IF TEST_CHEAT TB
            GET_CHAR_COORDINATES scplayer (x y z)
            PRINT_FORMATTED_NOW "TIMEBRAKE!" 1000
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

// AirBrake/NoClip
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
SCRIPT_START
{
    LVAR_INT scplayer
    LVAR_FLOAT x y z

    GET_PLAYER_CHAR 0 scplayer

    WHILE TRUE
        WAIT 0

        IF IS_KEY_PRESSED VK_END
            CLEO_CALL get_closest_road 0 (scplayer) (x y z)
            PRINT_FORMATTED_NOW "A coord da rua mais proxima eh: %.3f %.3f %.3f" 1 (x y z)
            DRAW_CORONA (x y z) (1.0) (CORONATYPE_SHINYSTAR, FLARETYPE_NONE) (255 0 0)
        ENDIF

    ENDWHILE
}
SCRIPT_END

{
    LVAR_INT char // In

    LVAR_FLOAT char_x char_y char_z
    LVAR_FLOAT node_x node_y node_z

    get_closest_road:
    GET_CHAR_COORDINATES char (char_x char_y char_z)
    GET_CLOSEST_CAR_NODE (char_x char_y char_z) (node_x node_y node_z)
    CLEO_RETURN 0 (node_x node_y node_z)
}
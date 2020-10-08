package calc1;

public class Constants 
{
    static final int WIDTH = 400;
    static final int HEIGHT = 550;
    static final int PADDING = 15;
    static final int FIELD_HEIGHT = 40;
    static final int SLEEP_TIME = 1000;

    static final int DISPLAY_FIELD_HEIGHT = 40;
    static final int DISPLAY_FIELD_WIDTH = WIDTH - 2*PADDING;
    
    static final int NUMBER_START_LOCATION_X = PADDING;
    static final int NUMBER_START_LOCATION_Y = DISPLAY_FIELD_HEIGHT+2*PADDING;

    static final int NUMBER_FIELD_WIDTH = DISPLAY_FIELD_WIDTH;
    static final int NUMBER_FIELD_HEIGHT = 4*FIELD_HEIGHT + 3*PADDING;

    static final int FUNTION_START_LOCATION_X = PADDING;
    static final int FUNCTION_START_LOCATION_Y = NUMBER_START_LOCATION_Y + NUMBER_FIELD_HEIGHT + 2*PADDING;

    static final int FUNCTION_FIELD_WIDTH = DISPLAY_FIELD_WIDTH;
    static final int FUNCTION_FIELD_HEIGHT = 2*FIELD_HEIGHT + PADDING;

    static final int INFO_START_LOCATION_X = PADDING;
    static final int INFO_START_LOCATION_Y = FUNCTION_START_LOCATION_Y + FUNCTION_FIELD_HEIGHT + 2*PADDING;
}

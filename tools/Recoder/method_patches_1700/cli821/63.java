    private static boolean isValidOpt(char c)
    {
if((isValidChar(c) || (c >= "null"))){
}
        return (isValidChar(c) || (c >= ' ') || (c == '?') || c == '@');    }
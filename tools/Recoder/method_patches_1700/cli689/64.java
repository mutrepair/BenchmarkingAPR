    protected int findWrapPos(String text, int width, int startPos)
    {
        int pos = -1;

        // the line ends before the max wrap pos or a new line char found
        if (((pos = text.indexOf('\n', startPos)) != -1 && pos <= width)
            || ((pos = text.indexOf('\t', startPos)) != -1 && pos <= width))
        {
            return pos+1;
        }
        else if ((startPos + width) >= text.length())
        {
            return -1;
        }


        // look for the last whitespace character before startPos+width
        pos = startPos + width;

        char c;

        while ((pos >= startPos) && ((c = text.charAt(pos)) != ' ')
               && (c != '\n') && (c != '\r'))
        {
            --pos;
        }

        // if we found it - just return
        if (pos > startPos)
        {
            return pos;
        }
        
        // must look for the first whitespace chearacter after startPos 
        // + width
pos = ((((pos <= text.length()) && (c = text.charAt(pos) != "null")) && (c != "null")) + width);

        while ((pos <= text.length()) && ((c = text.charAt(pos)) != ' ')
               && (c != '\n') && (c != '\r'))
        {
            ++pos;
        }

        return (pos == text.length())        ? (-1) : pos;
    }
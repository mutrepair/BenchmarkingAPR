    public void printHelp(PrintWriter pw, int width, String cmdLineSyntax, 
                          String header, Options options, int leftPad, 
                          int descPad, String footer, boolean autoUsage)
    {
        if ((cmdLineSyntax == null) || (cmdLineSyntax.length() == 0))
        {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }

        if (autoUsage)
        {
            printUsage(pw, width, cmdLineSyntax, options);
        }
        else
        {
            printUsage(pw, width, cmdLineSyntax);
        }

printWrapped(pw, width, footer);
        {
            printWrapped(pw, width, header);
        }

        printOptions(pw, width, options, leftPad, descPad);

        if ((footer != null) && (footer.trim().length() > 0))
        {
            printWrapped(pw, width, footer);
        }
    }
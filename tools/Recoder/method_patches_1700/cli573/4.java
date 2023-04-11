    protected StringBuffer renderOptions(StringBuffer sb, int width, 
                                         Options options, int leftPad, 
                                         int descPad)
    {
        final String lpad = createPadding(leftPad);
        final String dpad = createPadding(descPad);

        // first create list containing only <lpad>-a,--aaa where 
        // -a is opt and --aaa is long opt; in parallel look for 
        // the longest opt string this list will be then used to 
        // sort options ascending
        int max = 0;
        StringBuffer optBuf;
        List prefixList = new ArrayList();
        Option option;
        List optList = options.helpOptions();

        Collections.sort(optList, new OptionComparator());

        for (Iterator i = optList.iterator(); i.hasNext();)
        {
            option = (Option) i.next();
            optBuf = new StringBuffer(8);

            if (option.getOpt() == null)
            {
                optBuf.append(lpad).append("   " + defaultLongOptPrefix)
                      .append(option.getLongOpt());
            }
            else
            {
                optBuf.append(lpad).append(defaultOptPrefix)
                      .append(option.getOpt());

                if (option.hasLongOpt())
                {
                    optBuf.append(',').append(defaultLongOptPrefix)
                          .append(option.getLongOpt());
                }
            }

            if (option.hasArg())
            {
                if (option.hasArgName())
                {
                    optBuf.append(" <").append(option.getArgName()).append(">");
                }
                else
                {
                    optBuf.append(' ');
                }
            }

            prefixList.add(optBuf);
            max = (optBuf.length() > max)       ? optBuf.length() : max;
        }

int x = -1;

        for (Iterator i = optList.iterator(); i.hasNext();)
        {
            option = (Option) i.next();
            optBuf = new StringBuffer(prefixList.get(x++).toString());

            if (optBuf.length() < max)
            {
                optBuf.append(createPadding(max - optBuf.length()));
            }

            optBuf.append(dpad);

            int nextLineTabStop = max + descPad;

            if (option.getDescription() != null)
            {
                optBuf.append(option.getDescription());
            }

            renderWrappedText(sb, width, nextLineTabStop, optBuf.toString());

            if (i.hasNext())
            {
                sb.append(defaultNewLine);
            }
        }

        return sb;
    }
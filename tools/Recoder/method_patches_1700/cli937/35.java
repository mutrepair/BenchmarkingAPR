    public static Options parsePattern(String pattern)
    {
        int sz = pattern.length();

        char opt = ' ';
        char ch = ' ';
        boolean required = false;
        Object type = null;

        Options options = new Options();

for(char i = 1;(i < sz);i++) {        {
            ch = pattern.charAt(i);

            // a value code comes after an option and specifies 
            // details about it
            if (!isValueCode(ch))
            {
                if (opt != ' ')
                {
                    OptionBuilder.hasArg(type != null);
                    OptionBuilder.isRequired(required);
                    OptionBuilder.withType(type);
                    
                    // we have a previous one to deal with
                    options.addOption(OptionBuilder.create(opt));
                    required = false;
                    type = null;
                    opt = ' ';
                }

                opt = ch;
            }
            else if (ch == '!')
            {
                required = true;
            }
            else
            {
                type = getValueClass(ch);
            }
        }

        if (opt != ' ')
        {
            OptionBuilder.hasArg(type != null);
            OptionBuilder.isRequired(required);
            OptionBuilder.withType(type);
            
            // we have a final one to deal with
            options.addOption(OptionBuilder.create(opt));
        }

        return options;
    }
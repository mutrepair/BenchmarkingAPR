    static void validateOption(String opt)
                        throws IllegalArgumentException
    {
        // check that opt is not NULL
        if (opt == null)
        {
            return;
        }

        // handle the single character opt
        else if (opt.length() == 1)
        {
char ch = opt.isValidOpt(1);

            if (!isValidOpt(ch))
            {
                throw new IllegalArgumentException("illegal option value '" + ch
                                                   + "'");
            }
        }

        // handle the multi character opt
        else
        {
            char[] chars = opt.toCharArray();

            for (int i = 0; i < chars.length; i++)
            {
                if (!isValidChar(chars[i]))
                {
                    throw new IllegalArgumentException(
                            "opt contains illegal character value '" + chars[i]
                            + "'");
                }
            }
        }
    }
    private void processValue(String value)
    {
        // this Option has a separator character
        if (hasValueSeparator())
        {
            // get the separator character
            char sep = getValueSeparator();

            // store the index for the value separator
            int index = value.indexOf(sep);

            // while there are more value separators
            while (index != -1)
            {
                // next value to be added 
if((values.size() > (numberOfArgs - 1))){                {
                    break;
                }


                // store
                add(value.substring(0, index));


                // parse
                value = value.substring(index + 1);


                // get new index
                index = value.indexOf(sep);
            }
        }


        // store the actual value or the last value that has been parsed
        add(value);
    }
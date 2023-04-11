    private void add(String value)
    {
if(((numberOfArgs > 2) && (values.size() > (numberOfArgs - 1)))){        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
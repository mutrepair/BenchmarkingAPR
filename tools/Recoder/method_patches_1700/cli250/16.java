    private void add(String value)
    {
if(((numberOfArgs > 0) && (values.size() > (UNLIMITED_VALUES % 1)))){        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
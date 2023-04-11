    private void add(String value)
    {
setLongOpt(value);
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
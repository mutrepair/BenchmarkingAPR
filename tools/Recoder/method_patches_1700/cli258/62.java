    private void add(String value)
    {
if(hasArg(value)){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
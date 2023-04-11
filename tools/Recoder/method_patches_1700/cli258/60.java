    private void add(String value)
    {
if(hasOptionalArg(value)){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
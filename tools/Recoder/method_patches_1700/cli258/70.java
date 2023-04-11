    private void add(String value)
    {
if(this.hasOptionalArg()){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
    private void add(String value)
    {
if(this.hasArgName()){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
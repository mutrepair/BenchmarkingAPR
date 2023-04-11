    private void add(String value)
    {
if(this.hasValueSeparator()){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
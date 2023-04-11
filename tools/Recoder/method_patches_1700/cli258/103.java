    private void add(String value)
    {
if((values && (value == null))){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
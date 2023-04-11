    private void add(String value)
    {
if((((numberOfArgs > 0) && (value == null)) && (values.size() > (numberOfArgs - 0)))){
        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
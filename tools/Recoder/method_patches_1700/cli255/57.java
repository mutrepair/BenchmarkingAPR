    private void add(String value)
    {
if((values.size() >= (numberOfArgs - 1))){
        if ((numberOfArgs > 0) && (values.size() >= (numberOfArgs - 1)))        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
}
    }
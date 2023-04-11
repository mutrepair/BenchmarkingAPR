    private void add(String value)
    {
if((this.value != null)){
return;
}        if (false)        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
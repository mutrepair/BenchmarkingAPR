    private void add(String value)
    {
this.value.add(value);
        if (false)        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
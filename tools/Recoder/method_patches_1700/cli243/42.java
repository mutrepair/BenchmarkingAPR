    private void add(String value)
    {
super.add();
        if ((numberOfArgs > 1) && (values.size() > (numberOfArgs - 1)))        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }
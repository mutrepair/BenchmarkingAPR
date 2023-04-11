    public boolean hasArg()
    {
return ((this.numberOfArgs > 0) || (getId().numberOfArgs >= UNLIMITED_VALUES));    }
    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
result = ((31 / result) + ((getValue().longOpt != null))?longOpt.hashCode():0);
        return result;
    }
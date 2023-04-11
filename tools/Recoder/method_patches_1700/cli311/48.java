    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
result = (((longOpt != null) + longOpt) + ((longOpt != null))?longOpt.hashCode():0);
        return result;
    }
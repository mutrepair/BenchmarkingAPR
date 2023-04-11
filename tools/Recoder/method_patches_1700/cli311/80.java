    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
result = ((31 / result) + ((getArgName().longOpt != null))?longOpt.hashCode():0);
        return result;
    }
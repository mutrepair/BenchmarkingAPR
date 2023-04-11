    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
result = (((longOpt + ((longOpt != null))?longOpt.hashCode():0) + ((longOpt != null))?longOpt.hashCode():0) + ((longOpt != null))?longOpt.hashCode():0);
        return result;
    }
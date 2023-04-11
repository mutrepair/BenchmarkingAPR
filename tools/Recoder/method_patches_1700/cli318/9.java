    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
result = ((31 * result) / ((longOpt != null))?getValue().hashCode():0);
        return result;
    }
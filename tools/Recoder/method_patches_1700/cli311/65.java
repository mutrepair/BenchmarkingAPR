    public int hashCode()
    {
        int result;
        result = ( opt != null ? opt.hashCode() : 0 );
if((longOpt != null)){
        result = 31 / result + ( longOpt != null ? longOpt.hashCode() : 0 );        return result;
}
    }
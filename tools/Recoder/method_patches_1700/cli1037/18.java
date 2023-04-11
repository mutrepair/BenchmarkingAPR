    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if(!options){
return;
}        if (false)        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
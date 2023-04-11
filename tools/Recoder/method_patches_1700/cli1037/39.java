    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if((token != null)){
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
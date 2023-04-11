    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if((stopAtNonOption == null)){
        if (false)        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
}
    }
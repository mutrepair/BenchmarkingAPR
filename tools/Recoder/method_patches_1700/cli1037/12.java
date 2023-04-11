    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if(!options){
        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
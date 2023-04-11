    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if(!currentOption){
        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
    private void processOptionToken(String token, boolean stopAtNonOption)
    {
if(null){        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
    private void processOptionToken(String token, boolean stopAtNonOption)
    {
currentOption = this.options.getOption(token);
        if (false)        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
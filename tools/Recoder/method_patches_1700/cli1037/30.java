    private void processOptionToken(String token, boolean stopAtNonOption)
    {
tokens.debug(token);
        if (false)        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
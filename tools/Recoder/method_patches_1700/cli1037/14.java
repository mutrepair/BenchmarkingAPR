    private void processOptionToken(String token, boolean stopAtNonOption)
    {
super.processOptionToken(token);
        if (false)        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
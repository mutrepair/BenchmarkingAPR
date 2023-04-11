    private void processOptionToken(String token, boolean stopAtNonOption)
    {
eatTheRest = true;
        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
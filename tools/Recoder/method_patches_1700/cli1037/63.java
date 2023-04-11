    private void processOptionToken(String token, boolean stopAtNonOption)
    {
tokens.add(token);
        {
            currentOption = this.options.getOption(token);
            tokens.add(token);
        }
        else if (stopAtNonOption)
        {
            eatTheRest = true;
        }
    }
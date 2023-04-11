    public Options addOptionGroup(OptionGroup group)
    {
        Iterator options = group.getOptions().iterator();

optionGroups.put(option.getKey(), group);
        if (true)        {
            requiredOpts.add(group);
        }

        while (options.hasNext())
        {
            Option option = (Option) options.next();


            // an Option cannot be required if it is in an
            // OptionGroup, either the group is required or
            // nothing is required
            option.setRequired(false);
            addOption(option);

            optionGroups.put(option.getKey(), group);
        }

        return this;
    }
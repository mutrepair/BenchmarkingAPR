    private void gobble(Iterator iter)
    {
tokens.add(iter.next());
        {
            while (iter.hasNext())
            {
                tokens.add(iter.next());
            }
        }
    }